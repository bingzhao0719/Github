package com.bing.github.kotlin

import com.bing.github.kotlin.http.UserService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfileInfoPresenter constructor(fragment: ProfileInfoFragment) {
    private val fragment = fragment
    fun loadData(loginId:String) {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        val userService:UserService = retrofit.create(UserService::class.java)
        var call = userService.getUser(true,loginId)
        var deferred = async (CommonPool,CoroutineStart.LAZY) {
            call.execute()
        }
        async(UI) {
            var response = deferred.await()
            fragment.showProfileInfo(response.body())
        }


    }
}