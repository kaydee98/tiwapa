package com.twistedgeinc.apps.tiwapa


import android.widget.EditText
import com.twistedgeinc.apps.tiwapa.family.AddRelativeActivity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class AddARelativeBehaviour {

    var firstNameET: EditText? = null
    var lastNameET: EditText? = null
    var middleNameET: EditText? = null

    @Before
    fun setUp(){
        val activity = Robolectric.setupActivity(AddRelativeActivity::class.java)
        firstNameET = activity.findViewById<EditText>(R.id.firstName_EditText)
        middleNameET = activity.findViewById<EditText>(R.id.middleName_EditText)
        lastNameET = activity.findViewById<EditText>(R.id.lastName_EditText)


        firstNameET?.setText("Oluwatoyin")
        middleNameET?.setText("Olukayode")
        lastNameET?.setText("Dada")
    }

    @Test
    fun clickingAddRelative_shouldAddARelative() {

        assertThat(firstNameET?.text.toString(), `is`("Oluwatoyin"))
    }

}