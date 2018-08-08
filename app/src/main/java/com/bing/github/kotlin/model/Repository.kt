package com.bing.github.kotlin.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@SuppressLint("ParcelCreator")
@Parcelize
data class Repository(
        var id: String? = null,
        var name: String? = null,
        @SerializedName("full_name")
        var fullName: String? = null,
        @SerializedName("private")
        var repPrivate: Boolean = false,
        @SerializedName("html_url")
        var htmlUrl: String? = null,
        var description: String? = null,
        var language: String? = null,
        var owner: User? = null,

        @SerializedName("default_branch")
        var defaultBranch: String? = null,

        @SerializedName("created_at")
        var createdAt: Date? = null,
        @SerializedName("updated_at")
        var updatedAt: Date? = null,
        @SerializedName("pushed_at")
        var pushedAt: Date? = null,

        @SerializedName("git_url")
        var gitUrl: String? = null,
        @SerializedName("ssh_url")
        var sshUrl: String? = null,
        @SerializedName("clone_url")
        var cloneUrl: String? = null,
        @SerializedName("svn_url")
        var svnUrl: String? = null,

        var size: Int = 0,
        @SerializedName("stargazers_count")
        var stargazersCount: Int = 0,
        @SerializedName("watchers_count")
        var watchersCount: Int = 0,
        @SerializedName("forks_count")
        var forksCount: Int = 0,
        @SerializedName("open_issues_count")
        var openIssuesCount: Int = 0,
        @SerializedName("subscribers_count")
        var subscribersCount: Int = 0,

        var fork: Boolean = false,
        var parent: Repository? = null,
        @SerializedName("has_issues")
        var hasIssues: Boolean = false,
        @SerializedName("has_projects")
        var hasProjects: Boolean = false,
        @SerializedName("has_downloads")
        var hasDownloads: Boolean = false,
        @SerializedName("has_wiki")
        var hasWiki: Boolean = false,
        @SerializedName("has_pages")
        var hasPages: Boolean = false,
        var sinceStargazersCount: Int = 0

) : Parcelable {
    fun isForkEnable(): Boolean {
        return !fork && "" != owner?.login
    }
}