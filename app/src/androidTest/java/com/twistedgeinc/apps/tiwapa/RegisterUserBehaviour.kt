package com.twistedgeinc.apps.tiwapa

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RegisterUserBehaviour {

    @get:Rule
    val registerUserBehaviourRule: ActivityTestRule<RegisterUserActivity> = ActivityTestRule(RegisterUserActivity::class.java)


    @Test
    fun shouldRegisterUserWithEmailAndPassword() {
        val toastText: String = ""

        onView(withId(R.id.email_address)).perform(typeText("kayoded@twistedge.com"), closeSoftKeyboard())
        onView(withId(R.id.password1)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.register_button)).perform(click())



    }

    @Test
    fun shouldDisplayToastWhenAttemptToRegisterWithExistingEmail() {

    }

    @Test
    fun shouldProvideValidEmail() {

    }

    @Test
    fun shouldProvideValidPassword() {

    }

}