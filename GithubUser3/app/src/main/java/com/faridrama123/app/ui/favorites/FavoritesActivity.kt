package com.faridrama123.app.ui.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.app.R
import com.faridrama123.app.data.local.entity.SearchEntity
import com.faridrama123.app.databinding.ActivityFavoritesBinding
import com.faridrama123.app.factory.ViewModelFactory
import com.faridrama123.app.ui.search.SearchAdapter

class FavoritesActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityFavoritesBinding
    private lateinit var favAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()

        favAdapter = SearchAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewmodel = ViewModelProvider(this, factory)[FavoritesViewModel::class.java]

        viewmodel.getFavoritesUser.observe(this, {
            favAdapter.setData(it as ArrayList<SearchEntity>)
        })

        with(binding.rvFavorites) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favAdapter
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


}