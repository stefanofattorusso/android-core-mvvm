package com.stefattorusso.coremvvm.data.models

import com.google.gson.annotations.SerializedName

class RepoSearchResponse {

    @SerializedName("total_count")
    var totalCount: Int = -1

    @SerializedName("items")
    var repoList: List<RepoDTO> = ArrayList()
}