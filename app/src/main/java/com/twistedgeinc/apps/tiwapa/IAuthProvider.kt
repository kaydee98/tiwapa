package com.twistedgeinc.apps.tiwapa

interface IAuthProvider {
    var userId: String?
    var isAuthenticated: Boolean
    var displayName: String?
    var photoUrl: String?

    fun loginWithEmailPassword(email: String, password: String)
    fun singOut()

}