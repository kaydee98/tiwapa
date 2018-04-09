package com.twistedgeinc.apps.tiwapa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class RegisterUserActivity : AppCompatActivity() {

    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var passwordEditText2: EditText? = null
    private var firstNameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    //private var displayNameEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        emailEditText = findViewById(R.id.email_address)
        passwordEditText = findViewById(R.id.password1)

        passwordEditText2 = findViewById(R.id.password2)
        firstNameEditText = findViewById(R.id.first_name)
        lastNameEditText = findViewById(R.id.last_name)
        //displayNameEditText = findViewById(R.id.display_name)

        val registerButtom = findViewById<Button>(R.id.register_button)
        val signInButton = findViewById<Button>(R.id.signin_button)

        registerButtom.setOnClickListener({registerUser() })
        signInButton.setOnClickListener({gotoLoginActivity()})

    }

    private fun registerUser() {

        if( !isFormValid()) {
            return
        }

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val createUserTask = auth.createUserWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())

        createUserTask.addOnSuccessListener(this, OnSuccessListener { authResult: AuthResult ->
            //TODO: Progress Bar
            //TODO: Update additional User Information (DisplayName and PhotoURL)
            //TODO: Update FirstName and LastName (Write these to the user_profile nodes of the Database )
            //TODO: Return to Profile Page or Home?
            //TODO: Send Email Verification

            val mainIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        })

        createUserTask.addOnFailureListener(OnFailureListener { exception: Exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
        })

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
}
