package com.twistedgeinc.apps.tiwapa

import android.app.Activity
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.twistedgeinc.apps.tiwapa.family.AddRelativeActivity
import com.twistedgeinc.apps.tiwapa.family.MyFamilyActivity
import com.twistedgeinc.apps.tiwapa.family.SiblingFragment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment


@RunWith(RobolectricTestRunner::class)
class MyFamilyFeatureBehaviourTest {

    private lateinit var activity: Activity

    @Before
    fun setUp()
    {
         activity = Robolectric.setupActivity(MyFamilyActivity::class.java)
        val context =
        FirebaseApp.initializeApp(activity.applicationContext)

    }

    @Test
    fun shouldOpenAddRelativeActivity() {

        val addRelativeButton = activity.findViewById<FloatingActionButton>(R.id.add_relative_fab)

        addRelativeButton.performClick()
        val expectedIntent = Intent(activity, AddRelativeActivity::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }


    @Test
    fun shouldNavigateToSiblingTab() {

        val siblingFragment = SiblingFragment()
        startFragment( siblingFragment)

        val view = siblingFragment.view
        val siblingsRV = view?.findViewById(R.id.siblings_RecyclerView) as RecyclerView
        val adapter = siblingsRV.adapter

        //assertNotNull(siblingsRV.findViewHolderForAdapterPosition(0).itemView.findViewById<TextView>( R.id.firstName_TextView).text)
        assertTrue(adapter.itemCount > 0  )



    }


}