package com.example.githubandroidreposapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubandroidreposapp.data.GithubRepo

@Dao
interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(reposList: MutableList<GithubRepo>)

    @Query("select * from github_repos")
    fun getPagedRepos(): PagingSource<Int, GithubRepo>

    @Query("select * from github_repos")
    suspend fun getRepos(): List<GithubRepo>

    @Query("delete from github_repos")
    suspend fun deleteAllRepos()
}