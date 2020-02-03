package com.piotrk.sampleproject.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.piotrk.sampleproject.di.Injectable
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseDialogFragment<VM: BaseViewModel, BINDING: ViewDataBinding>(@LayoutRes private val layoutId: Int):DialogFragment(), Injectable {

    lateinit var binding: BINDING
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    fun setup(
        clazz: KClass<VM>,
        hasActivityLifespan: Boolean = true
    ) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
        viewModel = if (hasActivityLifespan) {
            ViewModelProvider(requireActivity(), viewModelProviderFactory).get(clazz.java)
        } else {
            ViewModelProvider(this, viewModelProviderFactory).get(clazz.java)
        }
        binding.setVariable(BR.viewModel, viewModel)
        requireActivity().supportFragmentManager.registerFragmentLifecycleCallbacks(
            ViewModelLifecycleF(
                viewModel
            ), true
        )
    }
}