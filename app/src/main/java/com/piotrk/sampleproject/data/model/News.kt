package com.piotrk.sampleproject.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    val title: String,
    val description: String?,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    val content: String?
): Serializable