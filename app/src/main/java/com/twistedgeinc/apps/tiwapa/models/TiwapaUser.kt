package com.twistedgeinc.apps.tiwapa.models

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

class TiwapaUser {

    fun signInWith(email: String, password: String): Boolean {
        //val auth: FirebaseAuth = FirebaseAuth.getInstance()
        var signInSucceed: Boolean = true

//        val signInWithEmailAndPasswordTask = auth.signInWithEmailAndPassword(email, password)
//
//        signInWithEmailAndPasswordTask.addOnSuccessListener(OnSuccessListener {
//            signInSucceed =  true
//        })
//
//        signInWithEmailAndPasswordTask.addOnFailureListener {
//            signInSucceed = false
//        }

        return signInSucceed

    }
}