package com.faridrama123.githubuser2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.githubuser2.ApiConfig
import com.faridrama123.githubuser2.response.DetailResponse
import com.faridrama123.githubuser2.response.ItemsItem
import com.faridrama123.githubuser2.response.SearchResponse
import com.faridrama123.githubuser2.ui.home.SearchViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel () {

    companion object {
        private const val TAG = "DetailViewModel"
    }

    private val listDetail = MutableLiveData<DetailResponse>()

    fun setDetail(username: String) {
        val client = ApiConfig.getApiService().detailUser(username)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    listDetail.value = response.body()
                } else {
                    Log.e(DetailViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(DetailViewModel.TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getDetail(): LiveData<DetailResponse> {
        return listDetail
    }

}