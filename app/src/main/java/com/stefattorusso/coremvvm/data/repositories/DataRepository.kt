package com.stefattorusso.coremvvm.data.repositories

import com.stefattorusso.coremvvm.data.models.RepoSearchResponse
import com.stefattorusso.coremvvm.data.services.ApiService
import com.stefattorusso.coremvvm.di.scope.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject

@ApplicationScope
class DataRepository @Inject constructor(private val githubService: ApiService) {

    fun getMessage(): String {
        return "Hello my message"
    }

    fun searchRepositories(query: String): Observable<RepoSearchResponse> = githubService.searchRepos(query)
}