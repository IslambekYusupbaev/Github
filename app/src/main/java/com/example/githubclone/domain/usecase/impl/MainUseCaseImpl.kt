package com.example.githubclone.domain.usecase.impl

import androidx.paging.PagingData
import com.example.githubclone.data.models.*
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.Flow

class MainUseCaseImpl(private val repo: MainRepository) : MainUseCase {

    override suspend fun loginApi(): Flow<ResultData<String>> = repo.loginApi()

    override suspend fun getUserProfileInfo(): Flow<ResultData<GetUserProfileInfoResponceData>> = repo.getUserProfileInfo()

    override suspend fun searchUsersByUsername(userName: String): Flow<ResultData<List<ItemsData>>> = repo.searchUsersByUsername(userName)

    override suspend fun getUserRepositories(): Flow<ResultData<List<GetUserRepositories>>> = repo.getUserRepositories()

    override suspend fun searchRepoByRepoName(repoName: String): Flow<PagingData<ItemsRepoData>> = repo.searchRepoByRepoName(repoName)
}