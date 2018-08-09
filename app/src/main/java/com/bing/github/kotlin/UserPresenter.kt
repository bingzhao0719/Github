package com.bing.github.kotlin

import com.bing.github.kotlin.http.GithubService
import com.bing.github.kotlin.model.SearchModel
import com.bing.github.kotlin.utils.HttpUtils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserPresenter constructor(repoInfoFragment: RepoUserFragment) {
    private val repoInfoFrag = repoInfoFragment
    fun loadData(searchModel:SearchModel) {
        repoInfoFrag.showLoading()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        val githubService:GithubService = retrofit.create(GithubService::class.java)
        var call = githubService.searchUsers(searchModel.keyword!!,
                searchModel.page,HttpUtils.PAGE_SIZE,searchModel.sort)
        var deferred = async (CommonPool,CoroutineStart.LAZY) {
            call.execute()
        }
        async(UI) {
            var response = deferred.await()
            repoInfoFrag.setNewData(response.body()!!.items)
        }


    }
}