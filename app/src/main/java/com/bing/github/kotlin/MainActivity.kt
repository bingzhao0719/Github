package com.bing.github.kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.bing.github.kotlin.adapter.MyFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val pagerAdapter = MyFragmentAdapter(supportFragmentManager)
        pagerAdapter.addFragment(HomeFragment())
        pagerAdapter.addFragment(DashboardFragment())
        pagerAdapter.addFragment(MeFragment())
        mViewPager.adapter = pagerAdapter
        mViewPager.offscreenPageLimit = 3
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
