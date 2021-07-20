package com.example.githubandroidreposapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubandroidreposapp.R
import com.example.githubandroidreposapp.data.GithubRepo
import com.example.githubandroidreposapp.databinding.ActivityGithubRepoDetailBinding
import com.example.githubandroidreposapp.utils.Constants.NOT_FOUND
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubRepoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGithubRepoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubRepoDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init() {
        val githubRepo = intent.getParcelableExtra<GithubRepo>(INTENT_KEY_GITHUB_REPO)
        binding.apply {
            tvRepoFullName.text = githubRepo?.fullName ?: NOT_FOUND
            tvRepoDescription.text = githubRepo?.description ?: NOT_FOUND
            tvStars.text = "${(githubRepo?.watchers ?: 0) / 1000}k"
            tvLanguage.text = githubRepo?.language ?: NOT_FOUND
            tvForks.text = "${(githubRepo?.forks ?: 0) / 1000}k"
            tvIssues.text = "${(githubRepo?.openIssues ?: 0) / 1000}k"
            if (githubRepo?.repoOwner?.avatarUrl != null) {
                Picasso.get().load(githubRepo.repoOwner.avatarUrl).error(R.drawable.ic_owner_type_32).into(imgAvatar);
            }
            tvOwnerName.text = githubRepo?.repoOwner?.login ?: NOT_FOUND
            tvOwnerType.text = githubRepo?.repoOwner?.type ?: NOT_FOUND
        }
    }
}