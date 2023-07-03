package com.example.githubclone.di

import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.repository.impl.MainRepositoryImpl
import com.example.githubclone.domain.usecase.MainUseCase
import com.example.githubclone.domain.usecase.impl.MainUseCaseImpl
import com.example.githubclone.presentation.MainViewModel
import com.example.githubclone.retrofit.GitHubApi
import com.example.githubclone.retrofit.GitHubInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val domainModule = module {


    single<MainRepository> {
        MainRepositoryImpl(api = get())
    }

    factory<MainUseCase> {
        MainUseCaseImpl(repo = get())
    }

    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

        val interceptor = GitHubInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .client(client)
            .build()
        return retrofit
    }

    fun provideApi(retrofit: Retrofit) : GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    single {
        provideRetrofit()
    }

    single {
        provideApi(retrofit = get())
    }

}

val appModule = module {
    factory<MainViewModel> {
        MainViewModel(useCase = get())
    }
}