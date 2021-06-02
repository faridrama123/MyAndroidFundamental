package com.faridrama123.app.ui.detail.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.app.data.GithubRepository
import com.faridrama123.app.data.local.entity.FollowersEntity
import com.faridrama123.app.data.remote.network.ApiConfig
import com.faridrama123.app.data.remote.response.FollowersResponse
import com.faridrama123.app.vo.Resource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowerViewModel (private val githubRepository: GithubRepository): ViewModel() {


    fun getFollower(username : String) : LiveData<Resource<List<FollowersEntity>>> =
        githubRepository.followersUser(username)



}