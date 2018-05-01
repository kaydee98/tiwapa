package com.twistedgeinc.apps.tiwapa

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.filters.LargeTest
import org.junit.Rule
import android.support.test.runner.AndroidJUnit4
import com.twistedgeinc.apps.tiwapa.family.AddRelativeActivity
import com.twistedgeinc.apps.tiwapa.family.MyFamilyActivity
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.Espresso.onData
import org.hamcrest.Matchers.*

import android.support.test.espresso.action.ViewActions.*


@RunWith(AndroidJUnit4::class)
@LargeTest
class AddRelativeInstrumentedTest {


    @get:Rule
    val addRelativeActivityRule = IntentsTestRule(AddRelativeActivity::class.java)

    @Test
    fun clickingAddRelative_shouldAddARelative(){
        whenFormDataFieldsAreValid()
        onView(withId(R.id.addRelative_Button)).perform(click())
        intended(hasComponent(MyFamilyActivity::class.java.name))
    }


    private fun whenFormDataFieldsAreValid() {

        onView(withId(R.id.firstName_EditText)).perform(typeText("Oyewole"))
        onView(withId(R.id.middleName_EditText)).perform(typeText("Olubayode"))
        onView(withId(R.id.lastName_EditText)).perform(typeText("Dada"))

        onView(withId(R.id.dateOfBirth_EditText)).perform(typeText("09/04/1969"), closeSoftKeyboard())
        onView(withId(R.id.male_RadioButton)).perform(click())

        onView(withId(R.id.relativeTypes_Spinner)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Sibling"))).perform(click())
    }

}