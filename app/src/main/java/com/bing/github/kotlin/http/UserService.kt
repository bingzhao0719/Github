package com.bing.github.kotlin.http

import com.bing.github.kotlin.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @GET("users/{user}")
    fun getUser(
            @Header("forceNetWork") forceNetWork: Boolean,
            @Path("user") user: String):
            Call<User>
}