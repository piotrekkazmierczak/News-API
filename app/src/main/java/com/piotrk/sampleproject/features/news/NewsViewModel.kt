package com.piotrk.sampleproject.features.news

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import com.piotrk.sampleproject.BR
import com.piotrk.sampleproject.OnItemClickListener
import com.piotrk.sampleproject.R
import com.piotrk.sampleproject.data.AppSchedulers
import com.piotrk.sampleproject.features.BaseViewModel
import com.piotrk.sampleproject.features.news.ui.NewsUIModel
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {

    val inProgress = ObservableBoolean(false)

    val newsItems: ObservableList<NewsUIModel> = ObservableArrayList()
    val newsItemsBinding: ItemBinding<NewsUIModel> =
        ItemBinding.of<NewsUIModel> { itemBinding, _, item ->
            itemBinding.set(BR.item, R.layout.item_news).bindExtra(BR.listener, listener)
        }

    private val newsSubject = PublishSubject.create<NewsUIModel>()

    private val listener: OnItemClickListener<NewsUIModel> =
        object : OnItemClickListener<NewsUIModel> {
            override fun onItemClick(item: NewsUIModel) {
                newsSubject.onNext(item)
            }
        }

    fun setup() {
        load(false)
    }

    fun reload() {
        load(true)
    }

    private fun load(isRefreshing: Boolean) {
        disposable.add(
            getNewsUseCase.getNews()
                .subscribeOn(appSchedulers.io())
                .observeOn(appSchedulers.main())
                .doOnSubscribe { inProgress.set(true) }
                .doOnSuccess { inProgress.set(false) }
                .subscribeWith(GetNewsObserver(isRefreshing))
        )
    }

    fun subscribeToNewsClicks(observer: DisposableObserver<NewsUIModel>) {
        disposable.add(
            newsSubject.subscribeWith(observer)
        )
    }

    inner class GetNewsObserver(private val isRefreshing: Boolean) :
        DisposableSingleObserver<List<NewsUIModel>>() {

        override fun onSuccess(list: List<NewsUIModel>) {
            if (isRefreshing)
                newsItems.clear()
            newsItems.addAll(list)
        }

        override fun onError(error: Throwable) {
            error.localizedMessage
        }

    }
}
