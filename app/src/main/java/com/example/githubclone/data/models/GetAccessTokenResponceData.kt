package com.example.githubclone.data.models

import com.google.gson.annotations.SerializedName

data class GetAccessTokenResponceData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val token_type: String
)
