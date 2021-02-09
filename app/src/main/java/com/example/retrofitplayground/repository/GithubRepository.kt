package com.example.retrofitplayground.repository

import android.util.Log
import com.example.retrofitplayground.model.Repo
import com.example.retrofitplayground.network.GitHubService
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class GithubRepository @Inject constructor(private val githubService: GitHubService) {

    companion object {
        private val TAG: String? = GithubRepository::class.simpleName
    }

    suspend fun getRepos(): List<Repo> {
        var result = listOf<Repo>()
        try {
            result = githubService.getRepos("JakeWharton")
            return result
        } catch (e: Throwable) {
            Log.d(TAG, e.toString())
        }
        return result
    }

    suspend fun downloadFile() {
        val response: Response<ResponseBody> = githubService.downloadFile("125")

        try {
            //you can now get your file in the InputStream
            val stream: InputStream? = response.body()?.byteStream()
            Log.d(TAG, "response: $response")
            Log.d(TAG, "Stream: $stream")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
