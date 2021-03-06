package com.stefattorusso.coremvvm.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

object UiStateBindingAdapters{

    @JvmStatic
    @BindingAdapter("uiState")
    fun setUiStateForLoading(progressView: ProgressBar, uiState: UIState) {
        progressView.visibility = when (uiState) {
            Loading -> View.VISIBLE
            else -> View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("uiState")
    fun setUiStateForLoadedContent(view: View, uiState: UIState) {
        view.visibility = when (uiState) {
            HasData -> View.VISIBLE
            else -> View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("emptyState")
    fun setUiStateForEmptyView(view: View, uiState: UIState) {
        view.visibility = when (uiState) {
            NoData -> View.VISIBLE
            else -> View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("errorState")
    fun setUiStateForErrorView(view: View, uiState: UIState) {
        view.visibility = when (uiState) {
            is Error -> View.VISIBLE
            else -> View.GONE
        }
    }
}