package com.bing.github.kotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

open class MyFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private val titleList: ArrayList<CharSequence> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    fun addFragment(fragment: Fragment,title: CharSequence = "") {
        titleList.add(title)
        fragmentList.add(fragment)
    }
    fun getFragment(position: Int):Fragment{
        return fragmentList[position]
    }
}