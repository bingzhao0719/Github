package com.bing.github.kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    var mFragments: ArrayList<Fragment> = ArrayList(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        mFragments.add(HomeFragment())
        mFragments.add(DashboardFragment())
        mFragments.add(MeFragment())
        val pagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return mFragments.size
            }

            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }
        }
        mViewPager.adapter = pagerAdapter
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> mBottomNav.selectedItemId = R.id.navigation_home
                    1 -> mBottomNav.selectedItemId = R.id.navigation_dashboard
                    2 -> mBottomNav.selectedItemId = R.id.navigation_notifications
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        mBottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    mViewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    mViewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    mViewPager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}
