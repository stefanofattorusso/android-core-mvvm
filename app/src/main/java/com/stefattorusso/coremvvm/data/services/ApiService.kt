package com.stefattorusso.coremvvm.data.services

import com.stefattorusso.coremvvm.data.models.RepoSearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    fun searchRepos(
        @Query("q") query: String
    ): Observable<RepoSearchResponse>
}