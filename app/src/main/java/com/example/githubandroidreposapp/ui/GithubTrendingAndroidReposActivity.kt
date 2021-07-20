package com.example.githubandroidreposapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubandroidreposapp.adapter.GithubRepoAdapter
import com.example.githubandroidreposapp.data.GithubRepo
import com.example.githubandroidreposapp.databinding.ActivityGithubTrendingAndroidReposBinding
import com.example.githubandroidreposapp.viewmodel.GithubReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val INTENT_KEY_GITHUB_REPO = "com.example.githubandroidreposapp.ui.GithubTrendingAndroidReposActivity.github_repo"

@AndroidEntryPoint
class GithubTrendingAndroidReposActivity : AppCompatActivity(), GithubRepoAdapter.OnItemClickListener {
    private lateinit var binding: ActivityGithubTrendingAndroidReposBinding
    private lateinit var githubReposViewModel: GithubReposViewModel
    private lateinit var githubRepoAdapter: GithubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubTrendingAndroidReposBinding.inflate(layoutInflater)
        githubReposViewModel = ViewModelProvider(this).get(GithubReposViewModel::class.java)
        val view = binding.root
        setContentView(view)
        init()
        setListeners()
    }

    private fun init() {
        githubRepoAdapter = GithubRepoAdapter(this)
        binding.apply {
            rvGithubTrendingReposList.layoutManager = LinearLayoutManager(applicationContext)
            rvGithubTrendingReposList.setHasFixedSize(true)
            rvGithubTrendingReposList.adapter = githubRepoAdapter
        }
        showCachedReposDbData()
    }

    private fun setListeners() {
        githubReposViewModel.githubPagedTrendingAndroidRepos.observe(this) {
            githubRepoAdapter.submitData(this.lifecycle, it)
            changeVisibilityIfReposPresent()
        }
    }

    private fun showCachedReposDbData() {
        lifecycleScope.launch {
            githubRepoAdapter.submitData(PagingData.from(githubReposViewModel.getGithubTrendingAndroidRepos()))
            changeVisibilityIfReposPresent()
        }
    }

    private fun changeVisibilityIfReposPresent() {
        if (githubRepoAdapter.itemCount == 0) {
            binding.apply {
                rvGithubTrendingReposList.visibility = View.GONE
                pbLoadRepos.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvGithubTrendingReposList.visibility = View.VISIBLE
                pbLoadRepos.visibility = View.GONE
            }
        }
    }

    override fun onItemClick(githubRepo: GithubRepo) {
        var githubRepoDetailIntent = Intent(this, GithubRepoDetailActivity::class.java)
        githubRepoDetailIntent.putExtra(INTENT_KEY_GITHUB_REPO, githubRepo)
        startActivity(githubRepoDetailIntent)
    }
}