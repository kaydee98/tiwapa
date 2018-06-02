package com.twistedgeinc.apps.tiwapa

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.twistedgeinc.apps.tiwapa.utils.CameraStoragePermissions
import com.twistedgeinc.apps.tiwapa.utils.ImageUtils
import java.util.*
import kotlin.Exception
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.UploadTask


/* TODO: [Begin TODO]
 1.a Add progress indicator
 1.b take a picture [DONE with bugs in emulator works fine on Samsung]
 2. Clean up email notification message etc
 3. Look through flow to ensure in case there's an error two things happen
 - 1. user account gets created
 - 2. user information is written to database
 - 3. Write file to database
 - 4. Update profile and display name
 - 5. send email verification 
 TODO: [End TODO]
*/

const val VALIDATE_PERMISSION_REQUEST_CODE = 10
const val TAG = "Register Activity:"
const val CAMERA_REQUEST_CODE = 10
const val GALLERY_REQUEST_CODE = 20
const val IMAGE_URL_PREFIX = "file://"
const val USER_PHOTOS_PATH = "user_photos"
const val PROFILE_PHOTOS = "profile_photos"
const val USER_PROFILE_NODE = "users"

class RegisterUserActivity : AppCompatActivity() {
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var passwordEditText2: EditText? = null
    private var firstNameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    private var profileImage: ImageView? = null

    private var mImagePath: String = ""
    private val imageUtils = ImageUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        emailEditText = findViewById(R.id.email_address)
        passwordEditText = findViewById(R.id.password1)

        passwordEditText2 = findViewById(R.id.password2)
        firstNameEditText = findViewById(R.id.first_name)
        lastNameEditText = findViewById(R.id.last_name)

        profileImage = findViewById<ImageView>(R.id.profile_image)
        val registerButton = findViewById<Button>(R.id.register_button)
        val signInButton = findViewById<Button>(R.id.signin_button)

        registerButton.setOnClickListener({registerUser() })
        signInButton.setOnClickListener({gotoLoginActivity()})
        profileImage!!.setOnClickListener( { addProfilePhoto() })

        val packageManager = packageManager
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
            Toast.makeText(this, "This device does not have a camera.", Toast.LENGTH_SHORT)
                    .show()
            this.finish()
        }


    }

    private fun addProfilePhoto() {

        if (checkPermissionsArray(CameraStoragePermissions.PERMISSIONS)) {

        } else {
            validatePermissionsArray(CameraStoragePermissions.PERMISSIONS)
        }

        //show Photo Popup Menu
        val popup = PopupMenu(this, profileImage)
        popup.setOnMenuItemClickListener { menuItem -> onPhotoMenuItemClicked( menuItem)  }
        popup.inflate(R.menu.picture_actions)
        popup.show()

    }

    private fun onPhotoMenuItemClicked(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.picture_action_camera -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
                return true
            }
            R.id.picture_action_gallery -> {
                val photoGalleryIntent = Intent(Intent.ACTION_PICK)
                photoGalleryIntent.type = "image/*"
                startActivityForResult(photoGalleryIntent, GALLERY_REQUEST_CODE)
                return true
            }
            else -> return false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            mImagePath = getImageUrlFromData(data!!)

            Picasso.get()
                    .load(IMAGE_URL_PREFIX + mImagePath)
                    .into(profileImage)
        }

    }

    fun getImageUrlFromData(data: Intent): String {
        var imageurl = ""
        val imageUri = data.data  //FIXME: On emulator this returns null but not on device


        if (imageUri != null) {
            val imageItem = imageUtils.getImageFromUri(this, imageUri)
            imageurl = imageItem.imageData
        }

        return imageurl
    }

    private fun registerUser() {

        if( !isFormValid()) {
            return
        }

        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
                .addOnSuccessListener(this,  { authResult: AuthResult -> uploadProfileImage(authResult) })
                .addOnFailureListener( { exception: Exception  -> logAndReportError( exception) } )
    }

    private fun uploadProfileImage(authResult: AuthResult) {

        val displayName = firstNameEditText?.text.toString() + " " + lastNameEditText?.text.toString()
        val currentUser = authResult.user

        if (mImagePath.isEmpty()) {

            updatedUserProfile(currentUser, displayName, null)

        } else {


            val profilePhotoStoragePath = "$USER_PHOTOS_PATH/${currentUser.uid}/$PROFILE_PHOTOS/${UUID.randomUUID().toString()}"
            val firebaseStorageRef = FirebaseStorage.getInstance().reference.child(profilePhotoStoragePath)

            val imageUploadTask = firebaseStorageRef.putBytes(imageUtils.compressImage( mImagePath))

            val urlTask = imageUploadTask.continueWithTask{ task ->
                if (!task.isSuccessful) {
                    throw task.exception!!
                }

                firebaseStorageRef.downloadUrl
            }

            urlTask.addOnSuccessListener { downloadUrl: Uri? ->

                updatedUserProfile(currentUser, displayName, downloadUrl)
                createNewUser(currentUser.uid)

            }

//            firebaseStorageRef.putBytes(imageUtils.compressImage( mImagePath))
//                    .addOnSuccessListener {taskSnapshot ->
//
//                        updatedUserProfile(currentUser, displayName, taskSnapshot.downloadUrl!!)
//                        createNewUser(currentUser.uid)
//
//                    }
//                    .addOnFailureListener{ exception -> logAndReportError( exception ) }
        }


        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()

    }

    private fun createNewUser(currentUserId: String) {
        val firestoreDB  = FirebaseFirestore.getInstance()
        val user =  mutableMapOf<String, Any>()

        user["firstname"] = firstNameEditText?.text.toString()
        user["lastname"] = lastNameEditText?.text.toString()

        firestoreDB.document("$USER_PROFILE_NODE/$currentUserId").set(user)
                .addOnSuccessListener( { Log.d(TAG, "Document added" ) })
                .addOnFailureListener( { exception -> logAndReportError(exception) })
    }

    private fun updatedUserProfile(authUser: FirebaseUser, displayName: String, profilePhotoUri: Uri? ) {

        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .setPhotoUri(profilePhotoUri)
                .build()

        authUser.updateProfile(userProfileChangeRequest)
                .addOnSuccessListener { authUser.sendEmailVerification() }
                .addOnFailureListener {  exception -> logAndReportError( exception ) }
    }

    private fun logAndReportError( exception: Exception){
        Log.d(TAG, exception.toString())
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun gotoLoginActivity() {
        val logInIntent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(logInIntent)
        finish()
    }

    private fun isFormValid(): Boolean {
        var valid = true
        var focusView: View? = null

        val email = emailEditText!!.text.toString()
        val password = passwordEditText!!.text.toString()
        val password2 = passwordEditText2!!.text.toString()
        //val displayName = displayNameEditText.getText().toString()
        val firstName = firstNameEditText!!.getText().toString()
        val lastName = lastNameEditText!!.text.toString()

        //Check for valid first name
        if (firstName.isEmpty()) {
            valid = false
            firstNameEditText!!.setError("First Name is Required")
            focusView = firstNameEditText!!
        }

        //Check for valid last name
        if (lastName.isEmpty()) {
            valid = false
            lastNameEditText!!.setError("First Name is Required")
            focusView = lastNameEditText!!
        }
        //Check for valid email
        if (email.isEmpty() || !email.contains("@")) {
            focusView = emailEditText!!
            valid = false
        }

        //check for valid password
        if (password.isEmpty() || (password.length < 4)) {
            passwordEditText!!.setError("Password is to short")
            focusView = passwordEditText
            valid = false

        }

        //Check for valid re-entered password
        if (password2.isEmpty() || (password != password2)) {
            passwordEditText2!!.setError("Passwords do not match")
            focusView = passwordEditText2
            valid = false
        }

        if (!valid) focusView!!.requestFocus()
        return valid

    }

    private fun checkPermissionsArray(permissions: Array<String>): Boolean {

        for (i in permissions.indices) {
            val permission = permissions[i]
            val permissionRequest = ActivityCompat.checkSelfPermission(this, permission)
            if (permissionRequest != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    private fun validatePermissionsArray(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, VALIDATE_PERMISSION_REQUEST_CODE)
    }
}
