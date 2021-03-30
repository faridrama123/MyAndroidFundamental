package com.faridrama123.githubuser2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.githubuser2.R
import com.faridrama123.githubuser2.databinding.ActivityDetailBinding
import com.faridrama123.githubuser2.ui.home.SearchViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "username"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = intent.getStringExtra(EXTRA_USERNAME)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val DetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        intent.getStringExtra(EXTRA_USERNAME)?.let {
            DetailViewModel.setDetail(it)
        }


        binding.progressBar.visibility = View.VISIBLE
        DetailViewModel.getDetail().observe(this, { it ->
            if (it != null) {

                binding.progressBar.visibility = View.GONE

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