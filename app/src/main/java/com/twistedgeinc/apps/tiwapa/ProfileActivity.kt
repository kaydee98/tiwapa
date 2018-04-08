package com.twistedgeinc.apps.tiwapa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class ProfileActivity: AppCompatActivity(), View.OnClickListener{
    private val MENU_ITEM_NUMBER: Int = 4
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        val signOutButton: Button = findViewById<Button>(R.id.signout_button)
        signOutButton.setOnClickListener(this)

        setUpBottonNavigationView()
    }

    override fun onClick(v: View?) {

        firebaseAuth.signOut()
    }

    private fun setUpBottonNavigationView() {
        val bottomNavigationViewEx = findViewById<BottomNavigationViewEx>(R.id.bottom_navigation)
        val bottomNavigationViewHelper = BottomNavigationViewHelper()

        bottomNavigationViewHelper.setupBottonNavigationView(bottomNavigationViewEx)
        bottomNavigationViewHelper.setNavigationItemSelectedLister(this@ProfileActivity,bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(MENU_ITEM_NUMBER)
        menuItem.isChecked = true


    }
}
