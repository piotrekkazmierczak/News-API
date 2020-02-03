package com.piotrk.sampleproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piotrk.sampleproject.features.news.NewsViewModel
import com.piotrk.sampleproject.features.news.dialog.NewsDialogViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsDialogViewModel::class)
    abstract fun bindNewsDialogViewModel(viewModel: NewsDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: NewsViewModelFactory): ViewModelProvider.Factory
}