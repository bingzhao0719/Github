package com.bing.github.kotlin


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.bing.github.kotlin.adapter.MyFragmentAdapter
import com.bing.github.kotlin.model.SearchModel
import com.bing.github.kotlin.utils.PrefUtils
import com.bing.github.kotlin.utils.ViewUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_appbar.*
import java.util.*

class HomeFragment : Fragment(), SearchView.OnQueryTextListener,
        MenuItem.OnActionExpandListener {

    private val TAG: String = "HomeFragment"
    private var mInputMode: Boolean = false
    private var mSearchModel = SearchModel()
    private lateinit var mAdapter: MyFragmentAdapter
    private lateinit var mActivity: AppCompatActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initSearchModel()
        setHasOptionsMenu(true)
        mActivity = activity as AppCompatActivity
        mTabLayout.setupWithViewPager(mViewPager)
        mAdapter = MyFragmentAdapter(childFragmentManager)
        mAdapter.addFragment(RepoFragment.start(), "REPO")
        mAdapter.addFragment(RepoUserFragment.start(), "USER")
        mViewPager.adapter = mAdapter
        mActivity.setSupportActionBar(mToolbar)
    }

    private fun initSearchModel() {
        mSearchModel.type = SearchModel.SearchType.Repository
        mSearchModel.keyword = "eventbus"
        mSearchModel.page = 1
    }

    fun getSearchModel(): SearchModel {
        return mSearchModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.menu_search, menu)
        var searchItem: MenuItem = menu.findItem(R.id.action_search)
        var searchView: SearchView = searchItem.actionView as SearchView
        searchView.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        if (mInputMode) {
            searchItem.expandActionView()
        } else {
            searchItem.collapseActionView()
        }
        searchItem.setOnActionExpandListener(this)
        searchView.setOnQueryTextListener(this)
        var autoCompleteTextView: AutoCompleteTextView = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)
        autoCompleteTextView.threshold = 0
        autoCompleteTextView.setAdapter<ArrayAdapter<*>>(ArrayAdapter(context,
                R.layout.layout_item_simple_list, getSearchRecordList()))
        autoCompleteTextView.setDropDownBackgroundDrawable(ColorDrawable(ViewUtils.getWindowBackground(context!!)))
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            onQueryTextSubmit(parent.adapter.getItem(position).toString())
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_info).isVisible = false
        if (mInputMode) {
            menu.findItem(R.id.action_sort).isVisible = false
            val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView
            searchView.setQuery("", false)
        } else {
            menu.findItem(R.id.action_sort).isVisible = mAdapter.count != 0
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.i(TAG, "onQueryTextSubmit:$query")
        if (TextUtils.isEmpty(query)) {
            return true
        }
        mInputMode = false
        search(query)
        setSubTitle()
        activity!!.invalidateOptionsMenu()
        addSearchRecord(query)
        return true
    }

    fun setSubTitle() {
        mActivity.supportActionBar!!.subtitle = mSearchModel.keyword
    }

    fun search(keyword: String) {
        mSearchModel.keyword = keyword
        when (mViewPager.currentItem) {
            0 -> {
                var fragment: RepoFragment = mAdapter.getFragment(0) as RepoFragment
                fragment.loadData(mSearchModel)
            }
            1 -> {
                var fragment: RepoUserFragment = mAdapter.getFragment(1) as RepoUserFragment
                fragment.loadData(mSearchModel)
            }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        mInputMode = true
        activity!!.invalidateOptionsMenu()
        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        mInputMode = false
        activity!!.invalidateOptionsMenu()
        return true
    }

    private fun getSearchRecordList(): ArrayList<String> {
        val records = PrefUtils.getSearchRecords()
        val recordList = ArrayList<String>()
        if (!TextUtils.isEmpty(records)) {
            val recordArray = records.split("\\$\\$".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            Collections.addAll(recordList, *recordArray)
        }
        return recordList
    }

    private fun addSearchRecord(record: String) {
        if (record.contains("$")) {
            return
        }
        val MAX_SEARCH_RECORD_SIZE = 30
        val recordList = getSearchRecordList()
        if (recordList.contains(record)) {
            recordList.remove(record)
        }
        if (recordList.size >= MAX_SEARCH_RECORD_SIZE) {
            recordList.removeAt(recordList.size - 1)
        }
        recordList.add(0, record)
        val recordStr = StringBuilder("")
        val lastRecord = recordList[recordList.size - 1]
        for (str in recordList) {
            recordStr.append(str)
            if (str != lastRecord) {
                recordStr.append("$$")
            }
        }
        PrefUtils.set(PrefUtils.SEARCH_RECORDS, recordStr.toString())
    }

}
