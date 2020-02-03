package com.piotrk.sampleproject.di

import com.piotrk.sampleproject.features.news.dialog.NewsDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsDialogFragment(): NewsDialogFragment

}