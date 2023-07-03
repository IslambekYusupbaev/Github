package com.example.githubclone.data.models

import com.google.gson.annotations.SerializedName

data class GetAccessTokenRequestBodyData(
    @SerializedName("client_id")
    val clientId : String,
    @SerializedName("client_secret")
    val clientSecret: String,
    val code: String
)
