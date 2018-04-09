package com.twistedgeinc.apps.tiwapa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var emailAddressEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailAddressEditText = findViewById<EditText>(R.id.email_address)
        passwordEditText = findViewById<EditText>(R.id.password)

        findViewById<Button>(R.id.signin_button).setOnClickListener({ loginUser()})
        findViewById<Button>(R.id.register_button).setOnClickListener( {gotoRegisterActivity()})

    }

    private fun gotoRegisterActivity() {
        val RegisterIntent: Intent = Intent(this, RegisterUserActivity::class.java)
        startActivity(RegisterIntent)
        finish()
    }

    private fun loginUser() {

        if( isValidForm()) {
            val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
            val authTask = firebaseAuth.signInWithEmailAndPassword(emailAddressEditText!!.text.toString(), passwordEditText!!.text.toString())

            authTask.addOnSuccessListener {
                val mainIntent: Intent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }

            authTask.addOnFailureListener {exception:Exception ->

                Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }

        } else
        {
            emailAddressEditText!!.requestFocus()
        }

    }

    private fun isValidForm(): Boolean {

        var bValid: Boolean = false

        bValid = (emailAddressEditText!!.text.isNotBlank() && passwordEditText!!.text.isNotBlank())

        return bValid

    }
}
