package com.stefattorusso.coremvvm.data.models

import com.google.gson.annotations.SerializedName

class RepoDTO {

    @SerializedName("id")
    var id: Int = -1

    @SerializedName("full_name")
    var fullName: String = ""

    @SerializedName("description")
    var description: String = ""

}