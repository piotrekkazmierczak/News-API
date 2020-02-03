package com.piotrk.sampleproject.di

import androidx.lifecycle.ViewModelProvider
import com.piotrk.sampleproject.NewsApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelModule::class,
        UseCaseProvidersModule::class,
        RepositoryProvidersModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {
    fun inject(app: NewsApplication)
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}