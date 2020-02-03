package com.piotrk.sampleproject.features.news.ui

import com.piotrk.sampleproject.R
import com.piotrk.sampleproject.data.NewsDateTime
import com.piotrk.sampleproject.data.YEAR_MONTH_DAY_HOUR_MIN_SEC_FORMATTER
import com.piotrk.sampleproject.data.model.News
import com.piotrk.sampleproject.util.StringProvider

object UIModelMapper {
    fun newsToUIModel(news: News, stringProvider: StringProvider): NewsUIModel {
        return NewsUIModel(
            news.title,
            news.description,
            news.urlToImage,
            NewsDateTime.parse(news.publishedAt, YEAR_MONTH_DAY_HOUR_MIN_SEC_FORMATTER),
            news.content,
            stringProvider.getString(R.string.no_content),
            stringProvider.getString(R.string.no_description)
        )
    }
}