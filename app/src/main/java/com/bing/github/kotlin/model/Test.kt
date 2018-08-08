package com.bing.github.kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Test(
        var name: String
) : Parcelable