package com.faridrama123.app.ui.detail

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.app.R
import com.faridrama123.app.data.local.entity.SearchEntity
import com.faridrama123.app.databinding.ActivityDetailBinding
import com.faridrama123.app.factory.ViewModelFactory
import com.faridrama123.app.ui.favorites.FavoritesActivity
import com.faridrama123.app.ui.settings.SettingsActivity
import com.faridrama123.app.vo.Status
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {


    private lateinit var binding : ActivityDetailBinding
    companion object {
        const val EXTRA = "extra"
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.tab_text_1,
                R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val factory = ViewModelFactory.getInstance(this)
        val DetailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val dataUser = intent.getParcelableExtra<SearchEntity>(EXTRA) as SearchEntity
        if (dataUser != null) {
            fetchDetailUser(dataUser.login,DetailViewModel)
            initfavorites(dataUser,DetailViewModel)
            initPager(dataUser.login)
        }


    }

    private fun initToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }




    private fun initPager (userview: String){
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = userview
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun initfavorites(data : SearchEntity, viewModel: DetailViewModel){
        var statusFavorite = data.isfavorite
        setStatusFavorite(statusFavorite)
        binding.fab.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavorites(data, statusFavorite)
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite (statusFavorite : Boolean){
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24_white))
        }

    }

    private fun fetchDetailUser(userview: String,viewModel: DetailViewModel){
        viewModel.setLogin(userview)
        viewModel.getDetailUser.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING ->
                        binding.progressBar.visibility = View.VISIBLE
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                .show()
                    }
                    Status.SUCCESS -> if (it.data != null) {
                        binding.progressBar.visibility = View.GONE
                        binding.username.text = it.data.login
                        binding.name.text = it.data.name
                        binding.company.text = it.data.company
                        binding.location.text = it.data.location
                        binding.repository.text = resources.getString(
                                R.string.repository,
                                it.data.publicRepos.toString()
                        )
                        binding.following.text = resources.getString(
                                R.string.following,
                                it.data.following.toString()
                        )
                        binding.followers.text = resources.getString(
                                R.string.followers,
                                it.data.followers.toString()
                        )

                        Glide.with(applicationContext)
                                .load(it.data.avatarUrl.toString()).circleCrop()
                                .apply(
                                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                                                .error(R.drawable.ic_error)
                                )
                                .into(binding.img1)
                    }
                }
            }
        })
    }
}