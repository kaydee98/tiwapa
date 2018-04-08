package com.twistedgeinc.apps.tiwapa

import org.junit.Assert.*
import org.junit.Test

class SignInUnitTest {
    @Test
    fun signInSuccess_ReturnsTrue() {
        val tiwapaUser: TiwapaUser = TiwapaUser()
        assertEquals(true, tiwapaUser.signInWith("kaydee98@gmail.com", "tasteNgr02"))
    }

    fun signInSuccess_ReturnFalse() {
        val tiwapaUser: TiwapaUser = TiwapaUser()
        assertEquals(false, tiwapaUser.signInWith("kayoded@microsoft.com", "tasteNgr02"))
    }

}