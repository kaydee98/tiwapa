package com.twistedgeinc.apps.tiwapa

import com.twistedgeinc.apps.tiwapa.models.GenderType
import com.twistedgeinc.apps.tiwapa.models.RelativeType
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import com.twistedgeinc.apps.tiwapa.models.TiwapaUser
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockito_kotlin.*

class TiwapaUserTest {

    lateinit var tiwapaAuthMock: IAuthProvider
    lateinit var kayodedada: TiwapaUser

    @Before
    fun setUp() {

        tiwapaAuthMock = mock {
           on {userId} doReturn "mTgzm7hIMNYuWpRd519lIQRt0c63"
           on {displayName} doReturn "Kayode Dada"
           on {photoUrl} doReturn "https://firebasestorage.googleapis.com/v0/b/com-twistedgeinc-tiwapa.appspot.com/o/user_photos%2FmTgzm7hIMNYuWpRd519lIQRt0c63%2Fprofile_photos%2F3a97a501-3574-4ecb-9bbd-9e7fd8911217?alt=media&token=444cbaab-0cd7-4bdb-baba-9a712465c60c"
        }

        kayodedada = TiwapaUser(tiwapaAuthMock)
        kayodedada.profile = TiwapaPerson("Oluwatoyin", "Olukayode", "Dada", GenderType.MALE, RelativeType.SELF )
    }

    @Test
    fun shouldHaveAValidUser()
    {
        assertEquals(kayodedada.userId,  "mTgzm7hIMNYuWpRd519lIQRt0c63")
    }

    @Test
    fun shouldNotBeAuthenticated() {
        assert(!kayodedada.isAuthenticated)
    }

    @Test
    fun shouldHaveValidProfileWithName()
    {
        assertThat(kayodedada.profile.firstName, `is` ("Oluwatoyin"))
    }

    @Test
    fun shouldHaveADisplayName() {
        assertThat(kayodedada.displayName, `is`( "Kayode Dada"))
    }

    @Test
    fun shouldHavePhotoUrl() {
        assertThat(kayodedada.photoUrl, `is`( "https://firebasestorage.googleapis.com/v0/b/com-twistedgeinc-tiwapa.appspot.com/o/user_photos%2FmTgzm7hIMNYuWpRd519lIQRt0c63%2Fprofile_photos%2F3a97a501-3574-4ecb-9bbd-9e7fd8911217?alt=media&token=444cbaab-0cd7-4bdb-baba-9a712465c60c"))
    }
}