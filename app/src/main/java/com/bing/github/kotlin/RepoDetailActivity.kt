package com.bing.github.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.bing.github.kotlin.adapter.MyFragmentAdapter
import com.bing.github.kotlin.model.Repository
import com.bing.github.kotlin.utils.StringUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_repo_detail.*
import java.util.*

class RepoDetailActivity : BaseActivity() {

    private lateinit var mCurrRepo: Repository

    companion object {
        @JvmStatic
        fun start(repository: Repository, context: Context) {
            val intent = Intent(context, RepoDetailActivity::class.java)
            intent.putExtra("repo", repository)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        mCurrRepo = intent.getParcelableExtra("repo")
        init()
    }

    private fun init() {
        setSupportActionBar(mToolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val pagerAdapter = MyFragmentAdapter(supportFragmentManager)
        pagerAdapter.addFragment(RepoInfoFragment.start(mCurrRepo), "INFO")
        pagerAdapter.addFragment(MeFragment(), "FILES")
        pagerAdapter.addFragment(MeFragment(), "COMMITS")
        pagerAdapter.addFragment(MeFragment(), "ACTIVITY")
        mViewPager.adapter = pagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        mDesc.text = mCurrRepo.description
        val language = if (TextUtils.isEmpty(mCurrRepo.language))
            getString(R.string.unknown)
        else
            mCurrRepo.language
        mInfo.text = String.format(Locale.getDefault(),
                "Language %s, size %s",
                language,
                StringUtils.getSizeString(mCurrRepo.size * 1024L))
        mToolbarLayout.title = mCurrRepo.name
        Glide.with(this)
                .load(mCurrRepo.owner!!.avatarUrl)
                .into(mIvUserAvater)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (mCurrRepo != null) {
            menuInflater.inflate(R.menu.menu_repository, menu)
            val starItem = menu.findItem(R.id.action_star)
            val bookmark = menu.findItem(R.id.action_bookmark)
            starItem.setTitle(R.string.star)
            starItem.setIcon(R.drawable.ic_un_star_title)
            menu.findItem(R.id.action_watch).setTitle(R.string.watch)
            menu.findItem(R.id.action_fork).setTitle(if (mCurrRepo.fork)
                R.string.forked
            else
                R.string.fork)
            menu.findItem(R.id.action_fork).isVisible = mCurrRepo.isForkEnable()
            bookmark.title = getString(R.string.bookmark)
            menu.findItem(R.id.action_wiki).isVisible = true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_star ->
                //starRepo(!mPresenter.isStarred());
                return true
            R.id.action_branch ->
                //mPresenter.loadBranchesAndTags();
                return true
            R.id.action_share ->
                //AppOpener.shareText(getActivity(), mPresenter.getRepository().getHtmlUrl());
                return true
            R.id.action_open_in_browser ->
                //AppOpener.openInCustomTabsOrBrowser(getActivity(), mPresenter.getRepository().getHtmlUrl());
                return true
            R.id.action_copy_url ->
                //AppUtils.copyToClipboard(getActivity(), mPresenter.getRepository().getHtmlUrl());
                return true
            R.id.action_copy_clone_url ->
                //AppUtils.copyToClipboard(getActivity(), mPresenter.getRepository().getCloneUrl());
                return true
            R.id.action_watch ->
                //mPresenter.watchRepo(!mPresenter.isWatched());
                //invalidateOptionsMenu();
                //showSuccessToast(mPresenter.isWatched() ?
                //      getString(R.string.watched) : getString(R.string.unwatched));
                return true
            R.id.action_fork ->
                //if(!mPresenter.getRepository().isFork()) forkRepo();
                return true
            R.id.action_releases ->
                //showReleases();
                return true
            R.id.action_wiki ->
                //WikiActivity.show(getActivity(), mPresenter.getRepository().getOwner().getLogin(),
                //                  mPresenter.getRepository().getName());
                return true
            R.id.action_download_source_zip ->
                //AppOpener.startDownload(getActivity(), mPresenter.getZipSourceUrl(),
                //                        mPresenter.getZipSourceName());
                return true
            R.id.action_download_source_tar ->
                //AppOpener.startDownload(getActivity(), mPresenter.getTarSourceUrl(),
                //                        mPresenter.getTarSourceName());
                return true
            R.id.action_bookmark ->
                //mPresenter.bookmark(!mPresenter.isBookmarked());
                //invalidateOptionsMenu();
                //showSuccessToast(mPresenter.isBookmarked() ?
                //                 getString(R.string.bookmark_saved) : getString(R.string.bookmark_removed));
                return true
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
