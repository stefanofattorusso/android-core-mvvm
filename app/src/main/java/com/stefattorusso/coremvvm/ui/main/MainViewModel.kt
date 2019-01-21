package com.stefattorusso.coremvvm.ui.main

import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.BaseViewModel
import com.stefattorusso.coremvvm.base.network.ApiSingleObserver
import com.stefattorusso.commons.ErrorData
import com.stefattorusso.coremvvm.data.models.RepoDTO
import com.stefattorusso.coremvvm.data.models.RepoSearchResponse
import com.stefattorusso.coremvvm.data.repositories.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

    private var msg: String = "test"
    var repoList: MutableLiveData<List<RepoDTO>> = MutableLiveData()

    fun getMessage(): String {
        msg = dataRepository.getMessage()
        return msg
    }

    fun searchRepos(query: String) {
        displayLoader(true)

        dataRepository.searchRepositories(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiSingleObserver<RepoSearchResponse>(compositeDisposable) {
                override fun onError(e: ErrorData) {

                }

                override fun onSuccess(data: RepoSearchResponse) {
                    displayLoader(false)
                    repoList.value = data.repoList
                }
            })
    }

    fun displayLoader(show: Boolean) {
        loader.value = show
    }
}