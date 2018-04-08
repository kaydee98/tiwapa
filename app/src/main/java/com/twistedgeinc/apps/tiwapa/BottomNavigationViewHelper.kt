package com.twistedgeinc.apps.tiwapa

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class BottomNavigationViewHelper {
    fun setupBottonNavigationView(bottomNavigationViewEx: BottomNavigationViewEx) {
        bottomNavigationViewEx.enableAnimation(false)
        bottomNavigationViewEx.enableShiftingMode(false)
        bottomNavigationViewEx.enableItemShiftingMode(false)
    }

    fun setNavigationItemSelectedLister(context: Context, bottomNavigationView: BottomNavigationViewEx) {

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when( menuItem.itemId) {
                R.id.menu_profile -> {

                 val profileIntent: Intent = Intent(context, ProfileActivity::class.java)
                 context.startActivity(profileIntent)

                }
                R.id.menu_search -> {}
                R.id.menu_home -> {

                    val mainIntent: Intent = Intent(context, MainActivity::class.java)
                    context.startActivity(mainIntent)

                }
                R.id.menu_myfamily -> {}
                R.id.menu_notification -> {}
            }

            return@setOnNavigationItemSelectedListener true
        }
    }
}