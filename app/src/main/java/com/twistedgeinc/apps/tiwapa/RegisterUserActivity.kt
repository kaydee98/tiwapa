package com.twistedgeinc.apps.tiwapa

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

class RegisterUserActivity : AppCompatActivity(), View.OnClickListener {

    private var mEmail: EditText? = null
    private var mPassword: EditText? = null
    private val mContext: Context? = Activity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        mEmail = findViewById<EditText>(R.id.email_address)
        mPassword = findViewById<EditText>(R.id.password1)


        val registerButtom = findViewById<Button>(R.id.register_button)
        val signInButton = findViewById<Button>(R.id.signin_button)

        registerButtom.setOnClickListener(this)
        signInButton.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.register_button -> {

                //TODO: Valid Data form and return appropriate errors
                val auth: FirebaseAuth = FirebaseAuth.getInstance()

                val createUserTask = auth.createUserWithEmailAndPassword(mEmail?.text.toString(), mPassword?.text.toString())

                createUserTask.addOnSuccessListener(this, OnSuccessListener { authResult: AuthResult ->
                    //TODO: Update additional User Information
                    //TODO: Update FirstName and LastName
                    //TODO: Update Profile Picture
                    //TODO: Return to Profile Page or Home?
                    //TODO: Send Email Verification

                    toast("Registation Successful for " + authResult.user.email, Toast.LENGTH_LONG)
                })

                createUserTask.addOnFailureListener(OnFailureListener { exception: Exception ->
                    //TODO: Display Error message
                    toast( exception.localizedMessage, Toast.LENGTH_LONG)
                })

            }
            R.id.signin_button -> {

                Toast.makeText(this, "SignIn Successful", Toast.LENGTH_LONG)
            }
        }
    }


    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

}
