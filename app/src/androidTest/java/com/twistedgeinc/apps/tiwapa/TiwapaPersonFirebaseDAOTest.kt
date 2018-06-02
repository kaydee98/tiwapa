package com.twistedgeinc.apps.tiwapa

import android.support.test.InstrumentationRegistry
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.twistedgeinc.apps.tiwapa.models.GenderType
import com.twistedgeinc.apps.tiwapa.models.RelativeType
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test

class TiwapaPersonFirebaseDAOTest {

    lateinit var userId: String
    val tiwapaPersonFirebaseDA = TiwapaPersonFirebaseDA()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()

        FirebaseApp.initializeApp(context)
        val auth = FirebaseAuth.getInstance()
        userId = auth.currentUser?.uid!!
    }

    @Test
    fun shouldGetRelatives() = runBlocking {
        val querySnapshot =  tiwapaPersonFirebaseDA.getAllRelatives(userId)
        assert(querySnapshot.size() > 0)
    }

    @Test
    fun shouldNotHaveRelatives() = runBlocking {
        val querySnapshot = tiwapaPersonFirebaseDA.getAllRelatives("test")
        assert(querySnapshot.size() == 0)
    }

    @Test
    fun shouldSaveRelative() {
        val tiwapaPerson = TiwapaPerson("LastKD", "LastMD", "LastName", GenderType.MALE, RelativeType.SELF)
        val saved = tiwapaPersonFirebaseDA.addRelative("test", tiwapaPerson)
        assert(saved)
    }
}