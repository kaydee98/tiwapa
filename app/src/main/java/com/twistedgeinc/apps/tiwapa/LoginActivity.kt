package com.twistedgeinc.apps.tiwapa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    var emailAddressEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailAddressEditText = findViewById<EditText>(R.id.email_address)
        passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.signin_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener(this)
        registerButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when(view.id){

            R.id.signin_button -> {

                if( isValidForm()) {
                    val emailAddress = emailAddressEditText!!.text.toString()
                    val password = passwordEditText!!.text.toString()
                    signInUser(emailAddress, password)
                } else
                {
                    emailAddressEditText!!.requestFocus()
                }
            }

            R.id.register_button -> {
                val RegisterIntent: Intent = Intent(this, RegisterUserActivity::class.java)
                startActivity(RegisterIntent)
                finish()
            }
        }

    }

    private fun isValidForm(): Boolean {

        var bValid: Boolean = false

        bValid = (emailAddressEditText!!.text.isNotBlank() && passwordEditText!!.text.isNotBlank())

        return bValid

    }

    private fun signInUser(emailAddress: String, password: String) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val authTask = firebaseAuth.signInWithEmailAndPassword(emailAddress, password)

        authTask.addOnSuccessListener() {
            val mainIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

        authTask.addOnFailureListener {exception:Exception ->

            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}
