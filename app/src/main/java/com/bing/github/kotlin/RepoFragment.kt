package com.bing.github.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bing.github.kotlin.adapter.RepoInfoAdapter
import com.bing.github.kotlin.model.Repository
import com.bing.github.kotlin.model.SearchModel
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_repo.*

class RepoFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener {

    private val TAG: String = "wubingzhao"

    private val mRepoInfoPresenter: RepoPresenter = RepoPresenter(this)
    private val mAdapter: RepoInfoAdapter = RepoInfoAdapter()
    private var mHomeFragment: HomeFragment? = null
    private var mDataLoaded: Boolean = false
    private var mLastKeyword: String = ""

    companion object {
        fun start(): RepoFragment {
            return RepoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        mHomeFragment = parentFragment as HomeFragment
        mRefreshLayout.setOnRefreshListener(this)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter
        mAdapter.onItemClickListener = this
        loadData(mHomeFragment!!.getSearchModel())
    }

    fun showLoading() {
        mRefreshLayout.isRefreshing = true
    }

    fun setNewData(data: List<Repository>?) {
        if (data == null)
            return
        Log.i(TAG, "setNewData data:" + data.size + "::" + Thread.currentThread().name)
        mRefreshLayout.isRefreshing = false
        mAdapter.setNewData(data)
    }

    override fun onRefresh() {
        mRefreshLayout.isRefreshing = true
        loadData(mHomeFragment!!.getSearchModel())
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Log.i(TAG, "onItemClick position:$position")
        var item: Repository = adapter.getItem(position) as Repository
        RepoDetailActivity.start(item, context!!)
    }

    fun loadData(searchModel: SearchModel) {
        mRepoInfoPresenter.loadData(searchModel)
        mLastKeyword = searchModel.keyword.toString()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.i(TAG, "isVisibleToUser:$isVisibleToUser mDataLoaded:$mDataLoaded")
        if (!isVisibleToUser)
            return
        if (mHomeFragment == null || mHomeFragment!!.getSearchModel() == null)
            return
        if (mLastKeyword != mHomeFragment!!.getSearchModel().keyword || !mDataLoaded) {
            mDataLoaded = true
            loadData(mHomeFragment!!.getSearchModel())
        }
    }


}
