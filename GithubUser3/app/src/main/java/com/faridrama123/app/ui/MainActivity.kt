package com.faridrama123.app.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.app.R
import com.faridrama123.app.data.local.entity.SearchEntity
import com.faridrama123.app.databinding.ActivityMainBinding
import com.faridrama123.app.factory.ViewModelFactory
import com.faridrama123.app.ui.contentprovider.DatabaseContract.NoteColumns.Companion.CONTENT_URI
import com.faridrama123.app.ui.detail.DetailActivity
import com.faridrama123.app.ui.favorites.FavoritesActivity
import com.faridrama123.app.ui.search.SearchAdapter
import com.faridrama123.app.ui.search.SearchViewModel
import com.faridrama123.app.ui.settings.SettingsActivity
import com.faridrama123.app.utils.Mapper
import com.faridrama123.app.vo.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchViewModel : SearchViewModel
    private lateinit var searchAdapter : SearchAdapter

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchAdapter = SearchAdapter()
        //loadNotesAsync()


        searchViewModel= ViewModelProvider(this, ViewModelFactory.getInstance(this))[SearchViewModel::class.java]

        searchViewModel.getSearch.observe(this, { it ->
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE

                    }
                    Status.SUCCESS -> if (it.data != null) {
                        binding.progressBar.visibility = View.GONE
                        searchAdapter.setData(it.data as ArrayList<SearchEntity>)
                        searchAdapter.notifyDataSetChanged()
                    }

                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }


                }

            }
        })


        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchAdapter
        }


    }

    private fun loadNotesAsync() {

        GlobalScope.launch(Dispatchers.Main) {

            val deferredNotes = async(Dispatchers.IO) {
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                Mapper.mapCursorToArrayList(cursor)
            }
            val notes = deferredNotes.await()

            if (notes!= null) {
                searchAdapter.setData(notes)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Input UserName.."


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.setSearch(query)
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }


        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorites -> {
                val i = Intent(this, FavoritesActivity::class.java)
                this.startActivity(i)
                true
            }

            R.id.setting -> {
                val i = Intent(this, SettingsActivity::class.java)
                this.startActivity(i)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }





}