package com.faridrama123.githubuser2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.githubuser2.ApiConfig
import com.faridrama123.githubuser2.response.FollowersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowerViewModel : ViewModel() {


    companion object{
        private const val TAG = "FollowerViewModel"
    }

    private val listitem  = MutableLiveData<List<FollowersResponse>>()

    fun setFollower (username : String){

        val client = ApiConfig.getApiService().listFollower(username)
        client.enqueue(object : Callback<List<FollowersResponse>>{
            override fun onResponse(
                call: Call<List<FollowersResponse>>,
                response: Response<List<FollowersResponse>>
            ) {
                if (response.isSuccessful){
                    listitem.value = response.body()
                }else{
                    Log.e(FollowerViewModel.TAG, "onFailureResponse : ${response.message()}")

                }
            }

            override fun onFailure(
                call: Call<List<FollowersResponse>>,
                t: Throwable
            ) {
                Log.e(FollowerViewModel.TAG, "onFailure: ${t.message.toString()}")
            }


        })

    }

    fun getFollower() : LiveData<List<FollowersResponse>> {
        return listitem
    }


}