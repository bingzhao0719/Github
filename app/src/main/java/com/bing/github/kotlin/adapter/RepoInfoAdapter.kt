package com.bing.github.kotlin.adapter

import android.text.TextUtils
import android.widget.ImageView
import com.bing.github.kotlin.R
import com.bing.github.kotlin.model.Repository
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class RepoInfoAdapter : BaseQuickAdapter<Repository, BaseViewHolder>(R.layout.layout_item_repo) {

    override fun convert(helper: BaseViewHolder, repository: Repository) {
        val hasOwnerAvatar = !TextUtils.isEmpty(repository.owner!!.avatarUrl)
        helper.setText(R.id.tv_repo_name, if (hasOwnerAvatar) repository.name else repository.fullName)
        helper.setText(R.id.tv_repo_description, repository.description)
        helper.setText(R.id.tv_star_num, repository.stargazersCount.toString())
        helper.setText(R.id.tv_fork_num, repository.forksCount.toString())
        helper.setText(R.id.tv_owner_name, repository.owner!!.login)

        if (TextUtils.isEmpty(repository.language)) {
            helper.setVisible(R.id.language_color, false)
            helper.setText(R.id.tv_language, "")
        } else {
            helper.setVisible(R.id.language_color, true)
            helper.setText(R.id.tv_language, repository.language)
            //                    int languageColor = LanguageColorsHelper.INSTANCE.getColor(context, repository.getLanguage());
            //                    holder.languageColor.setImageTintList(ColorStateList.valueOf(languageColor));
        }


        if (hasOwnerAvatar) {
            helper.setVisible(R.id.iv_user_avatar, true)
            helper.setVisible(R.id.tv_since_star_num, false)
            helper.setVisible(R.id.owner_lay, true)
            Glide.with(mContext)
                    .load(repository.owner!!.avatarUrl)
                    .into(helper.getView(R.id.iv_user_avatar) as ImageView)
        } else {
            helper.setVisible(R.id.iv_user_avatar, false)
            helper.setVisible(R.id.owner_lay, false)
            if (repository.sinceStargazersCount === 0) {
                helper.setVisible(R.id.since_star_lay, false)
            } else {
                helper.setVisible(R.id.since_star_lay, true)
                //                        switch (repository.getSince()) {
                //                            case Daily:
                //                                holder.tvSinceStarNum.setText(String.format(getString(R.string.star_num_today),
                //                                                                            repository.getSinceStargazersCount()));
                //                                break;
                //                            case Weekly:
                //                                holder.tvSinceStarNum.setText(String.format(getString(R.string.star_num_this_week),
                //                                                                            repository.getSinceStargazersCount()));
                //                                break;
                //                            case Monthly:
                //                                holder.tvSinceStarNum.setText(String.format(getString(R.string.star_num_this_month),
                //                                                                            repository.getSinceStargazersCount()));
                //                                break;
                //                        }
            }
        }

        helper.setVisible(R.id.fork_mark, repository.fork)
    }
}