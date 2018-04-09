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

class RegisterUserActivity : AppCompatActivity() {

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

        registerButtom.setOnClickListener({registerUser() })
        signInButton.setOnClickListener({gotoLoginActivity()})

    }

    private fun registerUser() {

        //TODO: Valid Data form and return appropriate errors
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        val createUserTask = auth.createUserWithEmailAndPassword(mEmail?.text.toString(), mPassword?.text.toString())

        createUserTask.addOnSuccessListener(this, OnSuccessListener { authResult: AuthResult ->
            //TODO: Update additional User Information
            //TODO: Update FirstName and LastName
            //TODO: Update Profile Picture
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
}
