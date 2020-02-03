package com.piotrk.sampleproject.di

import com.piotrk.sampleproject.data.NewsRepository
import com.piotrk.sampleproject.features.news.GetNewsUseCase
import com.piotrk.sampleproject.features.news.GetNewsUseCaseImpl
import com.piotrk.sampleproject.util.StringProvider
import dagger.Module
import dagger.Provides

@Module
open class UseCaseProvidersModule {
    @Provides
    fun provideGetNewsUseCaseProvider(
        newsRepository: NewsRepository,
        stringProvider: StringProvider
    ): GetNewsUseCase = GetNewsUseCaseImpl(newsRepository, stringProvider)
}