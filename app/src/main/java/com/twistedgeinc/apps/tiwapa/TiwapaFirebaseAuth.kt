package com.twistedgeinc.apps.tiwapa

import com.google.firebase.auth.FirebaseAuth

class TiwapaFirebaseAuth: IAuthProvider {

    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser

    override var userId: String?
        get() = currentUser?.uid
        set(value) {}
    override var isAuthenticated: Boolean
        get() = ( auth.currentUser != null )
        set(value) {}

    override var displayName: String?
        get() = currentUser?.displayName
        set(value) {}
    override var photoUrl: String?
        get() = currentUser?.photoUrl.toString()
        set(value) {}

    override fun loginWithEmailPassword(email: String, password: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun singOut() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}