package com.example.githubandroidreposapp.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.githubandroidreposapp.data.remote.GithubReposRemoteRepository
import java.lang.Exception

class GithubReposFetchWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workParams: WorkerParameters,
    val githubReposRemoteRepository: GithubReposRemoteRepository
) : CoroutineWorker(appContext, workParams) {

    override suspend fun doWork(): Result {
        return try {
            githubReposRemoteRepository.fetchSaveTrendingRepos()
            Result.success()
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.failure()
        }
    }
}