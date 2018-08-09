package com.bing.github.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bing.github.kotlin.model.User
import kotlinx.android.synthetic.main.fragment_profile_info.*

class ProfileInfoFragment : Fragment() {

    private lateinit var mUser:User
    private var mPresenter:ProfileInfoPresenter = ProfileInfoPresenter(this)

    companion object {
        fun start(user: User):ProfileInfoFragment{
            var fragment = ProfileInfoFragment()
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUser = arguments!!.getParcelable("user")
        mPresenter.loadData(mUser.login!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_info, container, false)
    }

    fun showProfileInfo(user: User?) {
        if (user == null){
            return
        }
        var nameStr = if (TextUtils.isEmpty(user.name)) user.login else user.name
        nameStr = if (user.isUser()) nameStr else "$nameStr(ORG)"
        name.text = nameStr

        bio.text = user.bio
        bio.visibility = if (TextUtils.isEmpty(user.bio)) View.GONE else View.VISIBLE

        followers_num_text.text = user.followers.toString()
        following_num_text.text = user.following.toString()
        repos_num_text.text = user.publicRepos.toString()
        gists_num_text.text = user.publicGists.toString()
        if (!user.isUser()) {
            members_lay.visibility = View.VISIBLE
            followers_lay.visibility = View.GONE
            following_lay.visibility = View.GONE
            gists_lay.visibility = View.GONE
        } else {
            members_lay.visibility = View.GONE
        }

        setTextView(company, user.company)
        setTextView(email, user.email)
        setTextView(link, user.blog)
    }

    private fun setTextView(textView: TextView, text: String?) {
        if (!TextUtils.isEmpty(text)) {
            textView.text = text
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }

}
