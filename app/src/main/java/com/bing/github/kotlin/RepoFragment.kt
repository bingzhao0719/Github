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
    private var mSearchModel = SearchModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        mRefreshLayout.setOnRefreshListener(this)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter
        mAdapter.onItemClickListener = this
        initSearchModel()
        mRepoInfoPresenter.loadData(mSearchModel)
    }

    private fun initSearchModel() {
        mSearchModel.type = SearchModel.SearchType.Repository
        mSearchModel.keyword = "eventbus"
        mSearchModel.page = 1
    }
    fun showLoading(){
        mRefreshLayout.isRefreshing = true
    }

    public fun setNewData(data: List<Repository>) {
        Log.i(TAG, "setNewData data:" + data.size + "::" + Thread.currentThread().name)
        mRefreshLayout.isRefreshing = false
        mAdapter.setNewData(data)
    }

    override fun onRefresh() {
        mRefreshLayout.isRefreshing = true
        mRepoInfoPresenter.loadData(mSearchModel)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Log.i(TAG, "onItemClick position:$position")
        var item: Repository = adapter.getItem(position) as Repository
        RepoDetailActivity.start(item, context!!)
    }


}
