package com.faridrama123.githubuser2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.githubuser2.ApiConfig
import com.faridrama123.githubuser2.response.ItemsItem
import com.faridrama123.githubuser2.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel () {

    companion object {
        private const val TAG = "SearchViewModel"
    }

    private val listItem = MutableLiveData<ArrayList<ItemsItem>>()
    private val search = MutableLiveData<SearchResponse>()

    fun setSearch(q: String) {
        val client = ApiConfig.getApiService().searchUser(q)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    search.value = response.body()
                    listItem.value = response.body()?.items as ArrayList<ItemsItem>?
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getSearch(): LiveData<ArrayList<ItemsItem>> {
        return listItem
    }
}

