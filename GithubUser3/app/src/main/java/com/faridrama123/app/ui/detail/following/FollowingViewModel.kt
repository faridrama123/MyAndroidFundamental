package com.faridrama123.app.ui.detail.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.app.data.GithubRepository
import com.faridrama123.app.data.local.entity.FollowersEntity
import com.faridrama123.app.data.local.entity.FollowingEntity
import com.faridrama123.app.data.remote.network.ApiConfig
import com.faridrama123.app.data.remote.response.FollowingResponse
import com.faridrama123.app.vo.Resource

import retrofit2.Call
import retrofit2.Response

class FollowingViewModel(private val githubRepository: GithubRepository) : ViewModel() {

    fun getFollowing(username : String) : LiveData<Resource<List<FollowingEntity>>> =
            githubRepository.followingUser(username)


}