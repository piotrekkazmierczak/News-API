package com.piotrk.sampleproject

import android.app.Activity
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.piotrk.sampleproject.di.AppComponent
import com.piotrk.sampleproject.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication: Application(), HasActivityInjector {

    @Inject
    open lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        initDateLibrary()
        initDagger()
    }

    private fun initDateLibrary() {
        AndroidThreeTen.init(this)
    }

    private fun initDagger() {
        appComponent = AppInjector.init(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}