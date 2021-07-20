package com.example.githubandroidreposapp.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "github_repos")
@Parcelize
data class GithubRepo(
    @PrimaryKey
    val id: String,
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    val description: String?,
    val language: String?,
    val forks: Long?,
    @SerializedName("open_issues")
    val openIssues: Long?,
    val watchers: Long?,
    @Embedded
    @SerializedName("owner")
    val repoOwner: GithubRepoOwner?
): Parcelable {

    @Parcelize
    data class GithubRepoOwner(
        val login: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        val type: String?
    ): Parcelable

}