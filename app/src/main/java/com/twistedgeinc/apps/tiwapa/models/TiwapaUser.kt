package com.twistedgeinc.apps.tiwapa.models

import com.twistedgeinc.apps.tiwapa.IAuthProvider


class TiwapaUser(authProvider: IAuthProvider) {

    private var _profile = TiwapaPerson()
    private var auth: IAuthProvider = authProvider

    var userId: String? = authProvider.userId
    var isAuthenticated: Boolean = authProvider.isAuthenticated
    var displayName: String? = authProvider.displayName
    var photoUrl: String? = authProvider.photoUrl

    var profile
        get() = _profile
        set(value) {_profile = value }

}