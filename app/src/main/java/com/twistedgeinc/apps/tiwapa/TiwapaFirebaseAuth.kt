package com.twistedgeinc.apps.tiwapa

class TiwapaFirebaseAuth: IAuthProvider {

    override var user_id: String
        get() = "KAYODED"
        set(value) {}
    override var isAuthenticated: Boolean
        get() = false
        set(value) {}

    override var displayName: String
        get() = "Kayode.Dada"
        set(value) {}
    override var photoUrl: String
        get() = "PhotoUrl"
        set(value) {}

    override fun loginWithEmailPassword(email: String, password: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun singOut() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}