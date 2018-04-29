package com.twistedgeinc.apps.tiwapa

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import com.twistedgeinc.apps.tiwapa.family.AddRelativeActivity
import com.twistedgeinc.apps.tiwapa.family.MyFamilyActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication



@RunWith(RobolectricTestRunner::class)
class myFamilyFeatureBehavihaviourTest {

    @Before
    fun setUp()
    {

    }

    @Test
    fun shouldOpenAddRelativeActivity() {
        val activity = Robolectric.setupActivity(MyFamilyActivity::class.java)
        val addRelativeButton = activity.findViewById<FloatingActionButton>(R.id.add_family_fab)

        addRelativeButton.performClick()
        val expectedIntent = Intent(activity, AddRelativeActivity::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }

}