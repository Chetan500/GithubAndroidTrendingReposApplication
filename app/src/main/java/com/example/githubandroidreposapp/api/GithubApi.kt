package com.example.githubandroidreposapp.api

import com.example.githubandroidreposapp.data.remote.GithubRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val DEFAULT_QUERY = "Android"
        const val DEFAULT_SORT = "stars"
    }

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String = DEFAULT_QUERY,
        @Query("sort") sort: String = DEFAULT_SORT,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
        ): Response<GithubRepoResponse>
}