package com.stefattorusso.coremvvm.ui.grid.view

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.GetRandomImageListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class GridViewModel @Inject constructor(
    private val getRandomImageListUseCase: GetRandomImageListUseCase,
    private val mImageModelMapper: ImageModelMapper
) : BaseViewModel() {

    private var imageList: MutableLiveData<List<Image>> = MutableLiveData()
    private var selectedItem: MutableLiveData<Pair<ImageView, Image>> = MutableLiveData()

    init {
        loadData()
    }

    fun onItemClicked(view: ImageView, position: Int) {
        val image = imageList.value?.get(position)
        image?.let {
            selectedItem.value = Pair(view, it)
        }
    }

    fun getImageModelList(): LiveData<List<ImageModel>> = Transformations.map(imageList) { list ->
        list.map { mImageModelMapper.transform(it) }
    }

    fun getSelectedItem(): LiveData<Pair<ImageView, Image>> = selectedItem

    fun loadData() {
        viewModelScope.launch {
            uiState.value = Loading
            when (val result = getRandomImageListUseCase.execute()) {
                is Outcome.Success -> {
                    imageList.value = result.value
                    uiState.value = NoData.takeIf { result.value.isNullOrEmpty() } ?: HasData
                }
                is Outcome.Error -> {
                    uiState.value = Error
                    error.value = result.cause
                }
            }
        }
    }
}