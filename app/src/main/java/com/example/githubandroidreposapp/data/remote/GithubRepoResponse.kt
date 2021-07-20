package com.example.githubandroidreposapp.data.remote

import com.example.githubandroidreposapp.data.GithubRepo
import com.google.gson.annotations.SerializedName

data class GithubRepoResponse(
    @SerializedName("total_count")
    val reposCount: Long,
    @SerializedName("items")
    val trendingRepos: MutableList<GithubRepo>
)