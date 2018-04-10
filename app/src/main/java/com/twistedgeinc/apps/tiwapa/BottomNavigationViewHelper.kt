package com.twistedgeinc.apps.tiwapa


import android.content.Context
import android.content.Intent
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

            val intent = when( menuItem.itemId) {

                R.id.menu_home -> Intent(context, MainActivity::class.java)
                R.id.menu_myfamily -> Intent(context, MainActivity::class.java) //TODO: Replace Placeholder Intent
                R.id.menu_search -> Intent(context, MainActivity::class.java) //TODO: Replace Placeholder Intent
                R.id.menu_notification -> Intent(context, MainActivity::class.java) //TODO: Replace Placeholder Intent
                R.id.menu_profile ->  Intent(context, ProfileActivity::class.java)
                else -> Intent(context, MainActivity::class.java)
            }

            context.startActivity(intent)
            return@setOnNavigationItemSelectedListener true
        }
    }
}