package com.example.retrofitplayground.network

import com.example.retrofitplayground.model.Repo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming


interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): List<Repo>

    @GET("https://test-assets-mobile.s3-us-west-2.amazonaws.com/{name}%402.zip")
    @Streaming
    suspend fun downloadFile(@Path("name") name: String): Response<ResponseBody>
}

