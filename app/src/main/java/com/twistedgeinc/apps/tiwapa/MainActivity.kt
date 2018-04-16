package com.twistedgeinc.apps.tiwapa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.twistedgeinc.apps.tiwapa.utils.BottomNavigationViewHelper

const val MENU_ITEM_NUMBER: Int = 0
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        val bottomNavigationViewEx = findViewById(R.id.bottom_navigation) as BottomNavigationViewEx
        val bottomNavigationViewHelper = BottomNavigationViewHelper()

        bottomNavigationViewHelper.setupBottonNavigationView(bottomNavigationViewEx)
        bottomNavigationViewHelper.setNavigationItemSelectedLister(this@MainActivity,bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(MENU_ITEM_NUMBER)
        menuItem.isChecked = true

    }
}
