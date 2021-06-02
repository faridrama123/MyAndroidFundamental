package com.faridrama123.app.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.faridrama123.app.data.GithubRepository
import com.faridrama123.app.data.local.entity.SearchEntity
import com.faridrama123.app.vo.Resource

class SearchViewModel (private val githubRepository: GithubRepository): ViewModel () {
    private var searchQuery = MutableLiveData<String>()


    fun setSearch(q: String)  {
        this.searchQuery.value = q
    }

    val getSearch = Transformations.switchMap(searchQuery) {
        githubRepository.searchUser(it)
    }



}

