package com.example.githubclone.domain.repository.impl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubclone.data.local.LocalStorage
import com.example.githubclone.data.models.ItemsRepoData
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.retrofit.GitHubApi
import com.example.githubclone.ui.paging.NETWORK_PAGE_SIZE
import com.example.githubclone.ui.paging.PageSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(private val api: GitHubApi) : MainRepository {

    override suspend fun loginApi() = flow {
        val responce =
            api.getAccessToken(
                "8f3cf5f09bd0c93a0528",
                "5447af3efb5afba3751aa6a0025e97affcf1a538",
                LocalStorage().code
            )

        Log.e("TTTT", responce.body()!!.accessToken)
        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.accessToken))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun getUserProfileInfo() = flow {
        val responce =
            api.getUserProfileInfo()


        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun searchUsersByUsername(userName: String) = flow {
        val responce =
            api.searchUsersByUsername(userName)

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.items))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun getUserRepositories() = flow {
        val responce =
            api.getUserRepositories()

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    override suspend fun searchRepoByRepoName(repoName: String): Flow<PagingData<ItemsRepoData>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory ={
                PageSource(service = api, repoName)
            }
        ).flow
    }
}