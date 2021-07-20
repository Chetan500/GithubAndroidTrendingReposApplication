package com.example.githubandroidreposapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.githubandroidreposapp.data.local.GithubReposLocalRepository

class GithubReposViewModel @ViewModelInject constructor(
    private val githubReposLocalRepository: GithubReposLocalRepository
) : ViewModel() {
    val githubPagedTrendingAndroidRepos = githubReposLocalRepository.getPagedTrendingRepos().liveData.cachedIn(viewModelScope)

    suspend fun getGithubTrendingAndroidRepos() = githubReposLocalRepository.getTrendingRepos()
}