package com.bing.github.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bing.github.kotlin.model.Repository
import com.bing.github.kotlin.utils.AppUtils
import com.bing.github.kotlin.utils.ViewUtils
import kotlinx.android.synthetic.main.fragment_repo_info.*

class RepoInfoFragment : Fragment() {

    private lateinit var mCurrRepo: Repository
    private var mRepoInfoPresenter = RepoInfoPresenter(this)

    companion object {
        @JvmStatic
        fun start(repository: Repository): RepoInfoFragment {
            var fragment = RepoInfoFragment()
            val bundle = Bundle()
            bundle.putParcelable("repo", repository)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCurrRepo = arguments!!.getParcelable("repo")
        mRepoInfoPresenter.setRepository(mCurrRepo)
        mRepoInfoPresenter.loadData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_info, container, false)
    }

    fun showRepoInfo(repository: Repository) {
        issueLay.visibility = if (repository.hasIssues) View.VISIBLE else View.GONE
        issuesNumText.text = repository.openIssuesCount.toString()
        stargazersNumText.text = repository.stargazersCount.toString()
        forksNumText.text = repository.forksCount.toString()
        watchersNumText.text = repository.subscribersCount.toString()

        val createStr = (if (repository.fork)
            getString(R.string.forked_at)
        else
            getString(R.string.created_at)) + " " + AppUtils
                .getDateStr(repository.createdAt!!)
        if (repository.pushedAt != null) {
            val updateStr = (getString(R.string.latest_commit) + " "
                    + AppUtils.getNewsTimeStr(context!!, repository.pushedAt!!))
            repoCreatedInfoText.text = String.format("%s, %s", createStr, updateStr)
        } else {
            repoCreatedInfoText.text = createStr
        }

        if (repository.fork && repository.parent != null) {
            forkInfoText.visibility = View.VISIBLE
            forkInfoText.text = getString(R.string.forked_from) + " " + repository.parent!!.fullName
        } else {
            forkInfoText.visibility = View.GONE
        }

        val fullName = repository.fullName
        val spannable = SpannableStringBuilder(fullName)
        spannable.setSpan(ForegroundColorSpan(ViewUtils.getAccentColor(context!!)),
                0, fullName!!.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {}

            override fun updateDrawState(ds: TextPaint) {

            }
        }, 0, fullName.indexOf("/"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        repoTitleText.movementMethod = LinkMovementMethod.getInstance()
        repoTitleText.text = spannable
    }

    fun showReadMe(page: String, baseUrl: String) {
        webView.loadDataWithBaseURL(baseUrl, page, "text/html", "utf-8", null)
    }
}
