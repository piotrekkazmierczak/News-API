package com.piotrk.sampleproject.di

import android.content.Context
import android.content.res.Resources
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.piotrk.sampleproject.NewsApplication
import com.piotrk.sampleproject.api.NewsApi
import com.piotrk.sampleproject.data.AppSchedulers
import com.piotrk.sampleproject.data.AppSchedulersImpl
import com.piotrk.sampleproject.util.ContextStringProvider
import com.piotrk.sampleproject.util.StringProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
open class AppModule(private val app: NewsApplication) {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .enableComplexMapKeySerialization()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()!!
    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideResources(): Resources = app.resources

    @Provides
    fun provideStringProvider(context: Context): StringProvider = ContextStringProvider(context)

    @Singleton
    @Provides
    fun provideAppSchedulers(): AppSchedulers = AppSchedulersImpl()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson
    ): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.addNetworkInterceptor(httpLoggingInterceptor)


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }
}

const val BASE_URL = "https://newsapi.org/"
const val TIMEOUT = 20L