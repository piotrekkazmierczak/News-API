package com.piotrk.sampleproject.di

import com.piotrk.sampleproject.features.news.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [(FragmentBuildersModule::class)])
    abstract fun contributeMainActivity(): NewsActivity
}