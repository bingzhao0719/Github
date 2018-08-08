package com.bing.github.kotlin

import android.text.TextUtils
import android.util.Log
import com.bing.github.kotlin.http.GithubService
import com.bing.github.kotlin.model.Repository
import com.bing.github.kotlin.utils.HtmlHelper
import com.bing.github.kotlin.utils.HttpUtils
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RepoInfoPresenter constructor(repoInfoFragment: RepoInfoFragment) {

    private val repoInfoFrag = repoInfoFragment

    private lateinit var repository: Repository
    var curBranch = ""
    private var readmeSource: String? = null
    fun loadData() {
        repoInfoFrag.showRepoInfo(repository!!)
        if (readmeSource == null) {
            loadReadMe()
        }
    }

    private fun loadReadMe() {
        val readmeFileUrl = (HttpUtils.GITHUB_API_BASE_URL + "repos/" + repository.fullName
                + "/" + "readme" + if (TextUtils.isEmpty(curBranch)) "" else "?ref=$curBranch")

        val branch = if (TextUtils.isEmpty(curBranch)) repository.defaultBranch else curBranch
        val baseUrl = (HttpUtils.GITHUB_API_BASE_URL + repository.fullName
                + "/blob/" + branch + "/" + "README.md")
        val retrofit = Retrofit.Builder()
                .baseUrl(HttpUtils.GITHUB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val githubService: GithubService = retrofit.create(GithubService::class.java)
        var call = githubService.getFileAsHtmlStream(true,readmeFileUrl)
        var deferred = async(CommonPool, CoroutineStart.LAZY) {
            Log.i("wubingzhao", "readmeFileUrl:$readmeFileUrl")
            var response = call.execute()
            readmeSource = response.body().string()
            Log.i("wubingzhao", "readmeSource:$readmeSource")
            HtmlHelper.generateMdHtml(readmeSource!!, baseUrl, false,
                    "", "", true)
        }
        async(UI) {
            var page= deferred.await()
            Log.i("wubingzhao", "page:$page")
            repoInfoFrag.showReadMe(page,baseUrl)
        }
    }

    fun setRepository(repo: Repository) {
        this.repository = repo
    }
}