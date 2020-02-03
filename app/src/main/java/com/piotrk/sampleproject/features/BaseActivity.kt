package com.piotrk.sampleproject.features

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.piotrk.sampleproject.BR
import com.piotrk.sampleproject.di.Injectable
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM: BaseViewModel, BINDING : ViewDataBinding>: DaggerAppCompatActivity(), Injectable {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    lateinit var binding: BINDING
    lateinit var viewModel: VM

    fun setup(layoutId: Int, clazz: Class<VM>) {
        try {
            binding = DataBindingUtil.setContentView(this, layoutId)
            viewModel = ViewModelProvider(this, viewModelProviderFactory).get(clazz)
            binding.setVariable(BR.viewModel, viewModel)
            application.registerActivityLifecycleCallbacks(
                ViewModelLifecycleA(
                    application,
                    viewModel
                )
            )
        } catch (e: UninitializedPropertyAccessException) {
            finish()
        }
    }

    protected fun showMessage(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}