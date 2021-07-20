package com.example.githubandroidreposapp.data.remote

import androidx.room.withTransaction
import com.example.githubandroidreposapp.api.GithubApi
import com.example.githubandroidreposapp.data.local.GithubRepoDatabase
import com.example.githubandroidreposapp.data.local.GithubReposLocalRepository
import com.example.githubandroidreposapp.utils.Constants.GITHUB_REPOS_PER_PAGE_SIZE
import com.example.githubandroidreposapp.utils.Constants.GITHUB_REPOS_STARTING_PAGE_INDEX
import kotlinx.coroutines.delay
import javax.inject.Inject

class GithubReposRemoteRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val githubRepoDb: GithubRepoDatabase,
    private val githubReposLocalRepository: GithubReposLocalRepository
) {

    suspend fun fetchSaveTrendingRepos() {
        var position = GITHUB_REPOS_STARTING_PAGE_INDEX

        try {
            while (position < 11) {
                delay(3000L)
                val searchReposResponse = githubApi.searchRepos(page = position, perPage = GITHUB_REPOS_PER_PAGE_SIZE)

                if (searchReposResponse.isSuccessful) {
                    val githubRepos = searchReposResponse.body()?.trendingRepos
                    delay(2000L)

                    githubRepoDb.withTransaction {
                        if (position == GITHUB_REPOS_STARTING_PAGE_INDEX) {
                            githubReposLocalRepository.deleteAllTrendingRepos()
                        }

                        if (githubRepos != null && !githubRepos.isEmpty()) {
                            githubReposLocalRepository.insertTrendingRepos(githubRepos)
                        } else {
                            position = 0
                        }
                    }

                    if (position == 0) break else position = position + 1
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

}