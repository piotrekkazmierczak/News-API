package com.piotrk.sampleproject.data

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface AppSchedulers {
    fun io(): Scheduler
    fun main(): Scheduler
    fun computation(): Scheduler
}

class AppSchedulersImpl : AppSchedulers {
    override fun computation() = Schedulers.computation()
    override fun main() = AndroidSchedulers.mainThread()!!
    override fun io() = Schedulers.io()
}