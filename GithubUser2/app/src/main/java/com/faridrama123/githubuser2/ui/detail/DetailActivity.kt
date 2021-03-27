package com.faridrama123.githubuser2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.githubuser2.R
import com.faridrama123.githubuser2.databinding.ActivityDetailBinding
import com.faridrama123.githubuser2.ui.home.SearchViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val DetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        intent.getStringExtra(EXTRA_USERNAME)?.let { DetailViewModel.setDetail(it) }
        DetailViewModel.getDetail().observe(this, { it ->
            if (it != null) {
                binding.username.text = it.login
                binding.name.text = it.name
                binding.company.text = it.company
                binding.location.text = it.location
                binding.repository.text =  resources.getString(R.string.repository,it.publicRepos.toString())
                binding.following.text =  resources.getString(R.string.following,it.following.toString())
                binding.followers.text =  resources.getString(R.string.followers,it.followers.toString())


                Glide.with(applicationContext)
                    .load(it.avatarUrl.toString()).centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.img1)

            }
        })

    }
}