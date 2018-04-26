package com.twistedgeinc.apps.tiwapa.models

import com.twistedgeinc.apps.tiwapa.IAuthProvider


class TiwapaUser(authProvider: IAuthProvider) {

    private var _profile = TiwapaPerson()
    var user_id: String = authProvider.user_id
    var isAuthenticated: Boolean = authProvider.isAuthenticated
    var displayName: String = authProvider.displayName
    var photoUrl: String = authProvider.photoUrl

    var profile
        get() = _profile
        set(value) {_profile = value }

}