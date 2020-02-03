package com.piotrk.sampleproject.di

import com.piotrk.sampleproject.api.NewsApi
import com.piotrk.sampleproject.data.NewsRepository
import com.piotrk.sampleproject.data.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
open class RepositoryProvidersModule {
    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)
}