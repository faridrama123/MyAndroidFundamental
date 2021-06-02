package com.faridrama123.app.data

import androidx.lifecycle.LiveData
import com.faridrama123.app.data.local.entity.*
import com.faridrama123.app.vo.Resource

interface GithubDataSource {



    fun searchUser (q : String): LiveData<Resource<List<SearchEntity>>>
    fun detailUser (username : String): LiveData<Resource<DetailEntity>>



    fun followingUser (username : String): LiveData<Resource<List<FollowingEntity>>>
    fun followersUser (username : String): LiveData<Resource<List<FollowersEntity>>>


    fun setFavoriteUser (data : SearchEntity, state : Boolean)
    fun getFavoriteUser () : LiveData<List<SearchEntity>>






}