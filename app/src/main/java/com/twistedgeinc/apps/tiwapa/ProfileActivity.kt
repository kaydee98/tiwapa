package com.twistedgeinc.apps.tiwapa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.squareup.picasso.Picasso
import com.twistedgeinc.apps.tiwapa.utils.BottomNavigationViewHelper

class ProfileActivity: AppCompatActivity() {
    private val MENU_ITEM_NUMBER: Int = 4
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val profileImageView = findViewById<ImageView>(R.id.profile_image)
        val firstNameTextView = findViewById<TextView>(R.id.user_firstname)
        findViewById<Button>(R.id.signout_button).setOnClickListener({signOut()})

        val profilePhotoUrl = firebaseAuth.currentUser!!.photoUrl
        Picasso.get().load(profilePhotoUrl).into(profileImageView)

        setUpBottonNavigationView()
    }

    fun signOut() {
        firebaseAuth.signOut()
        val loginInIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginInIntent)

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
