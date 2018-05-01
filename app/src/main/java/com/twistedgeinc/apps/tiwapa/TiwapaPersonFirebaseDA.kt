package com.twistedgeinc.apps.tiwapa

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson

class TiwapaPersonFirebaseDA: ITiwapaPersonDataAccess {
    override fun getAllRelatives(userId: String): ArrayList<TiwapaPerson> {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return emptyList<TiwapaPerson>() as ArrayList<TiwapaPerson>
    }

    override fun addRelative(userId: String, tiwapaPerson: TiwapaPerson): Boolean {
        val firestoreDB  = FirebaseFirestore.getInstance()

        firestoreDB.collection(USER_PROFILE_NODE).document(userId).collection("relatives").add(tiwapaPerson)
                .addOnSuccessListener( { Log.d(TAG, "Relative Added Successfully" ) })
                .addOnFailureListener( { exception -> Log.d(TAG, exception.toString())})

        return true
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
