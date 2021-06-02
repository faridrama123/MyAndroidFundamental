package com.faridrama123.app.ui.detail

import androidx.lifecycle.*
import com.faridrama123.app.data.GithubRepository
import com.faridrama123.app.data.local.entity.SearchEntity


class DetailViewModel (private  val githubRepository: GithubRepository) : ViewModel () {

    val id = MutableLiveData<String>()

    fun setLogin(q: String) {
         this.id.value = q
    }
    
    val getDetailUser =  Transformations.switchMap(id) {
        githubRepository.detailUser(it)
    }


    fun setFavorites(data: SearchEntity, state : Boolean){
        githubRepository.setFavoriteUser(data, state)
    }


}