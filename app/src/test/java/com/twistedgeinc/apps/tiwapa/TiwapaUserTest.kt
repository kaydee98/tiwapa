package com.twistedgeinc.apps.tiwapa

import com.twistedgeinc.apps.tiwapa.models.GenderType
import com.twistedgeinc.apps.tiwapa.models.RelativeType
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import com.twistedgeinc.apps.tiwapa.models.TiwapaUser
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test

class TiwapaUserTest {

    val tiwapaAuth = TiwapaFirebaseAuth()
    var kayodedada: TiwapaUser = TiwapaUser(tiwapaAuth)

    @Before
    fun setUp() {
        kayodedada.profile = TiwapaPerson("Oluwatoyin", "Olukayode", "Dada", GenderType.MALE, RelativeType.SELF )
    }

    @Test
    fun shouldHaveAValidUser()
    {
        assert(kayodedada.user_id == "KAYODED" )
    }

    @Test
    fun shouldNotBeAuthenticated() {
        assert(kayodedada.isAuthenticated == false)
    }

    @Test
    fun shouldHaveValidProfileWithName()
    {
        assertThat(kayodedada.profile.firstName, `is` ("Oluwatoyin"))
    }

    @Test
    fun shouldHaveADisplayName() {
        assertThat(kayodedada.displayName, `is`( "Kayode.Dada"))
    }

    @Test
    fun shouldHavePhotoUrl() {
        assertThat(kayodedada.photoUrl, `is`( "PhotoUrl"))
    }
}