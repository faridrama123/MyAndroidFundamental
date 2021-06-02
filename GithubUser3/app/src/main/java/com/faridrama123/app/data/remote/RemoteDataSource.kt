package com.faridrama123.app.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faridrama123.app.data.remote.network.ApiResponse
import com.faridrama123.app.data.remote.network.ApiService
import com.faridrama123.app.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(
    private val apiService: ApiService
){

    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null


        fun getInstance(apiService: ApiService): RemoteDataSource =
                instance ?: synchronized(this) {
                    RemoteDataSource(apiService).apply { instance = this }
                }


    }

    fun searchUser (q : String) : LiveData<ApiResponse<List<SearchResponseItems>>> {
        val results = MutableLiveData<ApiResponse<List<SearchResponseItems>>>()
        val client = apiService.searchUser(q)

        client.enqueue( object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val dataArray = response.body()?.items
                results.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                results.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })
        return results
    }


    fun detailUser (username : String) : LiveData<ApiResponse<DetailResponse>> {

        val results = MutableLiveData<ApiResponse<DetailResponse>>()
        val client = apiService.detailUser(username)

        client.enqueue( object : Callback<DetailResponse> {
            override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
            ) {
                val data = response.body()
                results.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                results.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })
        return results
    }


    fun followingUser(username : String) : LiveData<ApiResponse<List<FollowingResponse>>>{

        val client = apiService.listFollowing(username)
        val results = MutableLiveData<ApiResponse<List<FollowingResponse>>>()


        client.enqueue(object  : Callback<List<FollowingResponse>>{
            override fun onResponse(call: Call<List<FollowingResponse>>, response: Response<List<FollowingResponse>>) {
                val data = response.body()
                results.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<List<FollowingResponse>>, t: Throwable) {
                results.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }


        })

        return results

    }


    fun followersUser(username : String) : LiveData<ApiResponse<List<FollowersResponse>>>{

        val client = apiService.listFollower(username)
        val results = MutableLiveData<ApiResponse<List<FollowersResponse>>>()


        client.enqueue(object  : Callback<List<FollowersResponse>>{
            override fun onResponse(
                    call: Call<List<FollowersResponse>>,
                    response: Response<List<FollowersResponse>>
            ) {
                val data = response.body()
                results.value = if (data != null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<List<FollowersResponse>>, t: Throwable) {
                results.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }


        })

        return results

    }


}