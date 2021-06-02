package com.faridrama123.app.ui.favorites

import androidx.lifecycle.ViewModel
import com.faridrama123.app.data.GithubRepository

class FavoritesViewModel (private  val githubRepository: GithubRepository) : ViewModel (){


    val getFavoritesUser = githubRepository.getFavoriteUser()


}