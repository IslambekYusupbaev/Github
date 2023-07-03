package com.example.githubclone.domain.repository

import androidx.paging.PagingData
import com.example.githubclone.data.models.*
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun loginApi(): Flow<ResultData<String>>

    suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfoResponceData>>

    suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<ItemsData>>>

    suspend fun getUserRepositories(): Flow<ResultData<List<GetUserRepositories>>>

    suspend fun searchRepoByRepoName(repoName: String): Flow<PagingData<ItemsRepoData>>
}