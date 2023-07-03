package com.example.githubclone.data.models

data class SearchUsersByUsernameResponceData(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<ItemsData>
)


