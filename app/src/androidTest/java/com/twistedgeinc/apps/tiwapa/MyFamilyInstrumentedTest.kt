package com.twistedgeinc.apps.tiwapa

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.twistedgeinc.apps.tiwapa.family.MyFamilyActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf


@RunWith(AndroidJUnit4::class)
@LargeTest
class MyFamilyInstrumentedTest {

    @Test
    fun shouldDisplaySiblingsWhenInSiblingTab() {
        val matcher = allOf(withText(R.string.frag_sibling_title),
                isDescendantOfA(withId(R.id.family_tabs)))
        onView(matcher).perform(click())
        onView(withId(R.id.family_tabs)).check(matches(isCompletelyDisplayed()));

    }

    @Test
    fun shouldShowListOfSiblings() {


    }



}