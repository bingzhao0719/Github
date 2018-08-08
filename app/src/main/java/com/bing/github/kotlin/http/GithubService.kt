package com.bing.github.kotlin.http

import com.bing.github.kotlin.model.Repository
import com.bing.github.kotlin.model.SearchResult
import com.bing.github.kotlin.model.User
import com.bing.github.kotlin.utils.HttpUtils
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(
            @Query(value = "q", encoded = true) keyWord: String,
            @Query("page") page: Int,
            @Query("per_page") pageSize: Int = HttpUtils.PAGE_SIZE,
            @Query("sort") sort: String = "",
            @Query("order") order: String = "desc"):
            Call<SearchResult<Repository>>

    @GET("search/repositories")
    fun searchRepos2(
            @Query(value = "q", encoded = true) keyWord: String,
            @Query("page") page: Int,
            @Query("per_page") pageSize: Int):
            Call<Response<RequestBody>>

    @GET
    @Headers("Accept: application/vnd.github.html")
    fun getFileAsHtmlStream(
            @Header("forceNetWork") forceNetWork: Boolean,
            @Url url: String

    ): Call<ResponseBody>
    @GET("search/users")
    fun searchUsers(
            @Query(value = "q", encoded = true) keyWord: String,
            @Query("page") page: Int,
            @Query("per_page") pageSize: Int = HttpUtils.PAGE_SIZE,
            @Query("sort") sort: String = "",
            @Query("order") order: String = "desc"):
            Call<SearchResult<User>>
}