package com.twistedgeinc.apps.tiwapa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val mUser: FirebaseUser? = mAuth.currentUser

        if (mUser != null) {

            val mainIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()

        } else {

            val loginIntent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

    }
}
