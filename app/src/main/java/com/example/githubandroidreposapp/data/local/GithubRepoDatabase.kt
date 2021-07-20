package com.example.githubandroidreposapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubandroidreposapp.data.GithubRepo

@Database(entities = [GithubRepo::class], version = 1, exportSchema = false)
abstract class GithubRepoDatabase: RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}