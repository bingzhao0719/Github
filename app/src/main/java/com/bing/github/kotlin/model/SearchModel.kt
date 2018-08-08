package com.bing.github.kotlin.model

class SearchModel(
        var type: SearchType? = null,
        var keyword: String? = null,
        var sort: String = "",
        var desc: Boolean = true,
        var page: Int = 0
) {
    sealed class SearchType {
        object Repository : SearchType()
        object User : SearchType()
    }
}