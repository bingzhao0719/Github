package com.bing.github.kotlin.model

import com.google.gson.annotations.SerializedName

data class SearchResult<M>(
        @SerializedName("total_count")
        var totalCount: Int,
        @SerializedName("incomplete_results")
        var incompleteResults: Boolean,
        var items: List<M>
)