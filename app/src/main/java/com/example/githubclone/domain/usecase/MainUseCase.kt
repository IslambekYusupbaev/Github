package com.example.githubclone.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubclone.data.models.*
import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    suspend fun loginApi(): Flow<ResultData<String>>

    suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfoResponceData>>

    suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<ItemsData>>>

    suspend fun getUserRepositories(): Flow<ResultData<List<GetUserRepositories>>>

    suspend fun searchRepoByRepoName(repoName: String): Flow<PagingData<ItemsRepoData>>
}