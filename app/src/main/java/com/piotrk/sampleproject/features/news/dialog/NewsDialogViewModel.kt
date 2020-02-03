package com.piotrk.sampleproject.features.news.dialog

import androidx.databinding.ObservableField
import com.piotrk.sampleproject.features.BaseViewModel
import com.piotrk.sampleproject.features.news.ui.NewsUIModel
import javax.inject.Inject

class NewsDialogViewModel @Inject constructor() : BaseViewModel() {

    val content = ObservableField<String>()

    fun setup(newsUIModel: NewsUIModel) {
        content.set(newsUIModel.getFormattedContent())
    }
}