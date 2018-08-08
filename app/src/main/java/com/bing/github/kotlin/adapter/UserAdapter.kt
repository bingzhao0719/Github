package com.bing.github.kotlin.adapter

import android.widget.ImageView
import com.bing.github.kotlin.R
import com.bing.github.kotlin.model.User
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class UserAdapter : BaseQuickAdapter<User, BaseViewHolder>(R.layout.layout_item_user) {

    override fun convert(helper: BaseViewHolder, user: User) {
        helper.setText(R.id.name, user.login)
        Glide.with(mContext)
                .load(user.avatarUrl)
                .into(helper.getView(R.id.avatar) as ImageView)
    }
}