package com.piotrk.sampleproject.data

import com.piotrk.sampleproject.api.NewsApi
import com.piotrk.sampleproject.data.model.News
import io.reactivex.Single

interface NewsRepository {
    fun getNews(): Single<List<News>>
}

class NewsRepositoryImpl constructor(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getNews(): Single<List<News>> {
        return newsApi.getNews(listOf(NewsType.BUSINESS.key, NewsType.SPORT.key))
            .map { it.articles }
    }
}

enum class NewsType (val key: String) {
    SPORT("sport"), BUSINESS("business")
}