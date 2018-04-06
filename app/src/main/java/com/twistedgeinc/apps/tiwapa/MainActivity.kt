package com.twistedgeinc.apps.tiwapa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        val bottomNavigationViewEx = findViewById<BottomNavigationViewEx>(R.id.bottom_navigation) as BottomNavigationViewEx
        bottomNavigationViewEx.enableAnimation(false)
        bottomNavigationViewEx.enableShiftingMode(false)
        bottomNavigationViewEx.enableItemShiftingMode(false)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(0)
        menuItem.isChecked = true
    }
}
