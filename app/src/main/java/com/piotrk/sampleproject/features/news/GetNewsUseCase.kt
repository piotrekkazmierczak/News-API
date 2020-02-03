package com.piotrk.sampleproject.features.news

import com.piotrk.sampleproject.data.AppSchedulers
import com.piotrk.sampleproject.data.NewsRepository
import com.piotrk.sampleproject.features.news.ui.NewsUIModel
import com.piotrk.sampleproject.features.news.ui.UIModelMapper
import com.piotrk.sampleproject.util.StringProvider
import io.reactivex.Single

interface GetNewsUseCase {
    fun getNews(): Single<List<NewsUIModel>>
}

class GetNewsUseCaseImpl(
    private val newsRepository: NewsRepository,
    private val stringProvider: StringProvider
) : GetNewsUseCase {
    override fun getNews(): Single<List<NewsUIModel>> {
        return newsRepository.getNews()
            .map { news ->
                news.map { UIModelMapper.newsToUIModel(it, stringProvider) }
            }
    }
}