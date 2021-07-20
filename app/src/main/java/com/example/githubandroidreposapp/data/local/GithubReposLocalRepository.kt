package com.example.githubandroidreposapp.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.githubandroidreposapp.data.GithubRepo
import com.example.githubandroidreposapp.utils.Constants.GITHUB_REPOS_PAGE_SIZE
import com.example.githubandroidreposapp.utils.Constants.GITHUB_REPOS_PER_PAGE_SIZE
import javax.inject.Inject

class GithubReposLocalRepository @Inject constructor(private val githubRepoDao: GithubRepoDao) {

    suspend fun insertTrendingRepos(githubReposList: MutableList<GithubRepo>) = githubRepoDao.insertRepos(githubReposList)

    fun getPagedTrendingRepos() = Pager(
        config = PagingConfig(
            pageSize = GITHUB_REPOS_PAGE_SIZE,
            maxSize = GITHUB_REPOS_PER_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { githubRepoDao.getPagedRepos() }
    )

    suspend fun getTrendingRepos() = githubRepoDao.getRepos()

    suspend fun deleteAllTrendingRepos() = githubRepoDao.deleteAllRepos()
}