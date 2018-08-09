package com.bing.github.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.bing.github.kotlin.adapter.MyFragmentAdapter
import com.bing.github.kotlin.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile_activity.*

class ProfileActivity : BaseActivity() {

    private lateinit var mUser: User

    companion object {
        @JvmStatic
        fun start(user: User, context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("user", user)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_activity)
        mUser = intent.getParcelableExtra("user")
        init()
    }

    private fun init() {
        setSupportActionBar(mToolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val pagerAdapter = MyFragmentAdapter(supportFragmentManager)
        pagerAdapter.addFragment(ProfileInfoFragment.start(mUser), "INFO")
        pagerAdapter.addFragment(MeFragment(), "ACTIVITY")
        pagerAdapter.addFragment(MeFragment(), "STARRED")
        mViewPager.adapter = pagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        mLocation.text = mUser.location
        mJoinedTime.text = getString(R.string.created_at) + " " + mUser.createdAt
        mToolbarLayout.title = mUser.login
        Glide.with(this)
                .load(mUser.avatarUrl)
                .into(mUserAvatarBg)
        Glide.with(this)
                .load(mUser.avatarUrl)
                .into(mUserAvatar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
