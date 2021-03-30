package com.faridrama123.githubuser2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.githubuser2.ApiConfig
import com.faridrama123.githubuser2.ApiService
import com.faridrama123.githubuser2.response.FollowingResponse
import retrofit2.Call
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    companion object {
        private const val TAG = "FollowingViewModel"

    }

    private val listItem = MutableLiveData<List<FollowingResponse>>()

    fun setFollowing(username : String){

        val client = ApiConfig.getApiService().listFollowing(username)

        client.enqueue(object  : retrofit2.Callback<List<FollowingResponse>>{
            override fun onResponse(
                call: Call<List<FollowingResponse>>,
                response: Response<List<FollowingResponse>>
            ) {
                if (response.isSuccessful){
                    listItem.value = response.body()

                }else {
                    Log.e(FollowingViewModel.TAG, "onFailureResponse : ${response.message()}")

                }

            }

            override fun onFailure(call: Call<List<FollowingResponse>>, t: Throwable) {
                Log.e(FollowingViewModel.TAG, "onFailure ${t.message.toString()}")
            }


        })

    }

    fun getFollowing () : LiveData<List<FollowingResponse>>{
        return  listItem
    }



}