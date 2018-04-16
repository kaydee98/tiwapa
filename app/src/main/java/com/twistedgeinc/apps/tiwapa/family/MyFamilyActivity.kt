package com.twistedgeinc.apps.tiwapa.family

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.twistedgeinc.apps.tiwapa.R
import com.twistedgeinc.apps.tiwapa.utils.BottomNavigationViewHelper

const val MENU_ITEM_NUMBER = 1

class MyFamily : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_family)

        setUpBottomNavigationView()
        setupViewPager()
    }

    private fun setUpBottomNavigationView() {
        val bottomNavigationViewEx = findViewById(R.id.bottom_navigation) as BottomNavigationViewEx
        val bottomNavigationViewHelper = BottomNavigationViewHelper()

        bottomNavigationViewHelper.setupBottonNavigationView(bottomNavigationViewEx)
        bottomNavigationViewHelper.setNavigationItemSelectedLister(this@MyFamily,bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(MENU_ITEM_NUMBER)
        menuItem.isChecked = true

    }

    private fun setupViewPager() {

        var viewPageAdapter = ViewPageAdapter(supportFragmentManager)
        viewPageAdapter.addFragement(ParentFragment(), "Parents")
        viewPageAdapter.addFragement(SiblingFragment(), "Siblings")
        viewPageAdapter.addFragement(ChildrenFragment(), "Children")
        viewPageAdapter.addFragement(LifeEventsFragment(), "Life Events")

        var viewPager = findViewById<ViewPager>(R.id.fragment_view_pager)
        viewPager.adapter = viewPageAdapter

        var tabsLayout = findViewById<TabLayout>(R.id.familty_tabs)
        tabsLayout.setupWithViewPager(viewPager)

    }

    class ViewPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        val fragmentList = ArrayList<Fragment>()
        val fragmentTitlesList = ArrayList<String>()

        fun addFragement(fragment: Fragment, title: String): Unit {
            fragmentList.add(fragment)
            fragmentTitlesList.add(title)
        }
        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)

        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitlesList.get(position)
        }

    }
}
