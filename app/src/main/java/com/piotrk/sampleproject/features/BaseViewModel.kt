package com.piotrk.sampleproject.features

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    @Inject
    lateinit var resources: Resources

    protected var disposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    open fun onAttach() {}
    open fun onDetach() {}
}