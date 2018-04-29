package com.twistedgeinc.apps.tiwapa.family

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.twistedgeinc.apps.tiwapa.R
import com.twistedgeinc.apps.tiwapa.utils.BottomNavigationViewHelper

const val MENU_ITEM_NUMBER = 1

class MyFamilyActivity : AppCompatActivity() {
    private var addPersonFAB: FloatingActionButton? = null
    private var addEventFAB: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_family)

        setUpBottomNavigationView()
        setupViewPager()

        addPersonFAB = findViewById<FloatingActionButton>(R.id.add_family_fab).apply {
            setOnClickListener { addFamilyMember() }
        }

        addEventFAB = findViewById<FloatingActionButton>(R.id.add_event_fab).apply {
            setOnClickListener { addFamilyEvent() }
        }

    }

    private fun addFamilyMember() {
        val addRelativeIntent = Intent(this, AddRelativeActivity::class.java)
        startActivity(addRelativeIntent)
        finish()
    }

    private fun addFamilyEvent() {

    }
    private fun setUpBottomNavigationView() {
        val bottomNavigationViewEx = findViewById(R.id.bottom_navigation) as BottomNavigationViewEx
        val bottomNavigationViewHelper = BottomNavigationViewHelper()

        bottomNavigationViewHelper.setupBottonNavigationView(bottomNavigationViewEx)
        bottomNavigationViewHelper.setNavigationItemSelectedLister(this@MyFamilyActivity,bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(MENU_ITEM_NUMBER)
        menuItem.isChecked = true

    }

    private fun setupViewPager() {

        var viewPageAdapter = ViewPageAdapter(supportFragmentManager)
        viewPageAdapter.addFragment(ParentFragment(), getString(R.string.frag_parent_title))
        viewPageAdapter.addFragment(SiblingFragment(), getString(R.string.frag_sibling_title))
        viewPageAdapter.addFragment(ChildrenFragment(), getString(R.string.frag_children_title))
        viewPageAdapter.addFragment(LifeEventsFragment(), getString(R.string.frag_life_events_title))

        var viewPager = findViewById<ViewPager>(R.id.fragment_view_pager)
        viewPager.adapter = viewPageAdapter

        var tabsLayout = findViewById<TabLayout>(R.id.familty_tabs).apply {
            setupWithViewPager(viewPager)

            addOnTabSelectedListener( object: TabLayout.ViewPagerOnTabSelectedListener( viewPager) {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    super.onTabReselected(tab)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    super.onTabUnselected(tab)
                    if (tab?.text.toString() == getString(R.string.frag_life_events_title)) {
                        addEventFAB!!.visibility = View.GONE
                    } else {
                        addPersonFAB!!.visibility = View.GONE
                    }

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    super.onTabSelected(tab)
                   if (tab?.text.toString() == getString(R.string.frag_life_events_title)) {
                       addEventFAB!!.visibility = View.VISIBLE
                   } else {
                       addPersonFAB!!.visibility = View.VISIBLE
                   }
                }
            })
        }

    }

    class ViewPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        val fragmentList = ArrayList<Fragment>()
        val fragmentTitlesList = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String): Unit {
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
