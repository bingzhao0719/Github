package com.bing.github.kotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bing.github.kotlin.adapter.MyFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_appbar.*

class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setHasOptionsMenu(true)
        var activity = activity as AppCompatActivity
        mTabLayout.setupWithViewPager(mViewPager)
        var adapter = MyFragmentAdapter(childFragmentManager)
        adapter.addFragment("REPO",RepoFragment())
        adapter.addFragment("USER",RepoUserFragment())
        mViewPager.adapter = adapter
        activity.setSupportActionBar(mToolbar)
    }


}
