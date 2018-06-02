package com.twistedgeinc.apps.tiwapa

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception
import kotlin.coroutines.experimental.suspendCoroutine

class TiwapaPersonFirebaseDA: ITiwapaPersonDataAccess {
    private val firestoreDB  = FirebaseFirestore.getInstance()

    override suspend fun getAllRelatives(userId: String): QuerySnapshot = suspendCoroutine{ continuation ->

        firestoreDB.collection(USER_PROFILE_NODE).document(userId).collection("relatives").get()
                .addOnSuccessListener { querySnapshot -> continuation.resume(querySnapshot) }
                .addOnFailureListener { exception -> continuation.resumeWithException(exception)}
    }

    private fun logException(exception: Exception) {


    }

    override fun addRelative(userId: String, tiwapaPerson: TiwapaPerson): Boolean {

        var saved = false
        firestoreDB.collection(USER_PROFILE_NODE).document(userId).collection("relatives").add(tiwapaPerson)
                .addOnSuccessListener( { saved = true})
                .addOnFailureListener( { exception -> Log.d(TAG, exception.toString())})

        return saved
    }

    override fun deleteRelative(tiwapaPerson: TiwapaPerson): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }

    override fun updateRelative(tiwapaPerson: TiwapaPerson): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }



}
