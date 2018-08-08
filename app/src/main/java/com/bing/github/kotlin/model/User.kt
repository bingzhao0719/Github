package com.bing.github.kotlin.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@SuppressLint("ParcelCreator")
@Parcelize
data class User(
        val login: String? = null,
        var id: String? = null,
        var name: String? = null,
        @SerializedName("avatar_url")
        val avatarUrl: String? = null,
        @SerializedName("html_url")
        val htmlUrl: String? = null,
//        val type: UserType? = null,
        val company: String? = null,
        val blog: String? = null,
        val location: String? = null,
        val email: String? = null,
        val bio: String? = null,

        @SerializedName("public_repos")
        val publicRepos: Int = 0,
        @SerializedName("public_gists")
        val publicGists: Int = 0,
        val followers: Int = 0,
        val following: Int = 0,
        @SerializedName("created_at")
        val createdAt: Date? = null,
        @SerializedName("updated_at")
        val updatedAt: Date? = null
) : Parcelable {
    sealed class UserType {
        object Repository : UserType()
        object User : UserType()
    }

    fun isUser(): Boolean {
//        return SearchModel.SearchType.User == type
        return true
    }
}
