package com.example.githubclone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.githubclone.data.models.*
import com.example.githubclone.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.*

class MainViewModel(private val useCase: MainUseCase) : ViewModel() {

    val getSuccessFlow = MutableSharedFlow<String>()
    val getMessageFlow = MutableSharedFlow<String>()
    val getErrorFlow = MutableSharedFlow<Throwable>()

    val getUserPrInfoSuccessFlow = MutableSharedFlow<GetUserProfileInfoResponceData>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<GetUserRepositories>>()
    val getSearchUserFlow = MutableSharedFlow<List<ItemsData>>()
    val searchReposRepoNameFlow = MutableSharedFlow< PagingData<ItemsRepoData>>()

    suspend fun isSuccess() {
        useCase.loginApi().onEach {
            when(it) {
                is ResultData.Success -> {
                    getSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserProfileInfo() {
        useCase.getUserProfileInfo().onEach {
            when(it) {
                is ResultData.Success -> {
                    getUserPrInfoSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchUsersByUserName(userName: String) {
        useCase.searchUsersByUsername(userName).onEach {
            when (it) {
                is ResultData.Success -> {
                    getSearchUserFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserRepositories() {
        useCase.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchRepoByRepoName(repoName: String) {
        useCase.searchRepoByRepoName(repoName).onEach {
            searchReposRepoNameFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}