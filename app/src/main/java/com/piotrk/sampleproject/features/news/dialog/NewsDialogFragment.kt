package com.piotrk.sampleproject.features.news.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.piotrk.sampleproject.R
import com.piotrk.sampleproject.databinding.FragmentDialogNewsBinding
import com.piotrk.sampleproject.features.BaseDialogFragment
import com.piotrk.sampleproject.features.news.ui.NewsUIModel

class NewsDialogFragment: BaseDialogFragment<NewsDialogViewModel, FragmentDialogNewsBinding>(R.layout.fragment_dialog_news) {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setup(NewsDialogViewModel::class)
        setupArgs()

        return AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
            .apply {
                setView(binding.root)
            }
            .create()

    }

    private fun setupArgs() {
        val newsUIModel = arguments?.getSerializable(NEWS_UI_MODEL) as? NewsUIModel
        newsUIModel?.let{
            viewModel.setup(it)
        }
    }

    companion object {
        const val NEWS_UI_MODEL ="ui_model"
        fun newInstance(newsUIModel: NewsUIModel) = NewsDialogFragment().apply {
            arguments = Bundle().apply {
                putSerializable(NEWS_UI_MODEL, newsUIModel)
            }
        }
    }
}
