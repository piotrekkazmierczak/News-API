package com.piotrk.sampleproject.features.news

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.piotrk.sampleproject.OnItemClickListener
import com.piotrk.sampleproject.R
import com.piotrk.sampleproject.databinding.ActivityNewsBinding
import com.piotrk.sampleproject.features.BaseActivity
import com.piotrk.sampleproject.features.news.dialog.NewsDialogFragment
import com.piotrk.sampleproject.features.news.ui.NewsUIModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class NewsActivity : BaseActivity<NewsViewModel, ActivityNewsBinding>(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = dispatchingFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_news, NewsViewModel::class.java)
        setupRefreshLayout()
        setupViewModel()

    }

    private fun setupViewModel() {
        viewModel.setup()
        viewModel.subscribeToNewsClicks(clickObserver)
    }

    private fun setupRefreshLayout() {
        binding.bookingsSwipeRefreshLayout.setOnRefreshListener {
            viewModel.reload()
        }
    }

    private val clickObserver = object: DisposableObserver<NewsUIModel>() {
        override fun onComplete() {}

        override fun onNext(item: NewsUIModel) {
            NewsDialogFragment.newInstance(item).apply {
                show(supportFragmentManager, "news_dialog")
            }
        }

        override fun onError(error: Throwable) {
            showMessage(error)
        }
    }

}