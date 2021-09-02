package com.lazior.demonews.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("urlToImage")
    val url: String,
    var title: String
)
