package com.piotrk.sampleproject.features.news.ui

import com.piotrk.sampleproject.data.DAY_NAME_MONTH_NAME_DAY_FORMATTER
import com.piotrk.sampleproject.data.NewsDateTime
import java.io.Serializable

class NewsUIModel(
    val title: String,
    val description: String?,
    val imageUrl: String?,
    private val publishedDate: NewsDateTime,
    val content: String?,
    val noContentString: String,
    val noDescriptionString: String
): Serializable {
    fun getFormattedDate() = publishedDate.format(DAY_NAME_MONTH_NAME_DAY_FORMATTER)
    fun getFormattedContent() = content ?: noContentString
    fun getFormattedDescription() = description ?: noDescriptionString
}