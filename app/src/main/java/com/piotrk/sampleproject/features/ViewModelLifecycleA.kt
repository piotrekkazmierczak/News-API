package com.piotrk.sampleproject.features

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel

class ViewModelLifecycleA<out VIEWMODEL : BaseViewModel>(
    private val application: Application,
    private val viewModel: VIEWMODEL
): Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(a: Activity?, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(a: Activity?) {
        viewModel.onAttach()
    }

    override fun onActivityResumed(a: Activity?) {}
    override fun onActivityPaused(a: Activity?) {}
    override fun onActivityStopped(a: Activity?) {
        viewModel.onDetach()
    }

    override fun onActivitySaveInstanceState(a: Activity?, outState: Bundle?) {}
    override fun onActivityDestroyed(a: Activity?) {
        application.unregisterActivityLifecycleCallbacks(this)
    }
}

class ViewModelLifecycleF<out VM : BaseViewModel>(
    val viewModel: VM
) : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        viewModel.onAttach()
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        viewModel.onDetach()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        fm.unregisterFragmentLifecycleCallbacks(this)
    }
}
