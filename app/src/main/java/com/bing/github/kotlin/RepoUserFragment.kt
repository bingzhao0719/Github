package com.bing.github.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bing.github.kotlin.adapter.UserAdapter
import com.bing.github.kotlin.model.SearchModel
import com.bing.github.kotlin.model.User
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_repo.*

class RepoUserFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener {

    private val TAG: String = "wubingzhao"
    private val mUserPresenter: UserPresenter = UserPresenter(this)
    private val mAdapter: UserAdapter = UserAdapter()
    private var mSearchModel = SearchModel()
    private var mDataLoaded:Boolean = false

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
    }

    private fun initSearchModel() {
        mSearchModel.type = SearchModel.SearchType.Repository
        mSearchModel.keyword = "eventbus"
        mSearchModel.page = 1
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.i(TAG, "isVisibleToUser:$isVisibleToUser mDataLoaded:$mDataLoaded")
        if (isVisibleToUser && !mDataLoaded && mSearchModel != null) {
            mDataLoaded = true
            mUserPresenter.loadData(mSearchModel)
        }
    }
    fun showLoading(){
        mRefreshLayout.isRefreshing = true
    }

    public fun setNewData(data: List<User>) {
        Log.i(TAG, "setNewData user data:" + data.size + "::" + Thread.currentThread().name)
        mRefreshLayout.isRefreshing = false
        mAdapter.setNewData(data)
    }

    override fun onRefresh() {
        mRefreshLayout.isRefreshing = true
        mUserPresenter.loadData(mSearchModel)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        Log.i(TAG, "onItemClick position:$position")
        var user:User = adapter.getItem(position) as User
        ProfileActivity.start(user,context!!)
    }

}
