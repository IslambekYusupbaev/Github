package com.example.githubclone.retrofit

import com.example.githubclone.data.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface GitHubApi {

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String
    ): Response<GetAccessTokenResponceData>

    @GET("/user")
    suspend fun getUserProfileInfo() : Response<GetUserProfileInfoResponceData>

    @GET("/search/users?q")
    suspend fun searchUsersByUsername(@Query("q") username: String) : Response<SearchUsersByUsernameResponceData>

    @GET("/user/repos")
    suspend fun getUserRepositories() : Response<List<GetUserRepositories>>

    @GET("/search/repositories?q")
    suspend fun searchRepoByRepoName(@Query("q") repoName: String, @Query("page") page: Int) : Response<SearchRepoByRepoNameResponceData>

}