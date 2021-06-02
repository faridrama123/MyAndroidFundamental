package com.faridrama123.app.data.remote.network

import com.faridrama123.app.data.remote.response.DetailResponse
import com.faridrama123.app.data.remote.response.FollowersResponse
import com.faridrama123.app.data.remote.response.FollowingResponse
import com.faridrama123.app.data.remote.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUser (
     @Query("q") q : String
    ): Call <SearchResponse>


    @GET("users/{username}")
    fun detailUser (
        @Path("username")  username : String
    ) :Call<DetailResponse>


    @GET("users/{username}/followers")
    fun listFollower (
        @Path("username") username : String
    ) : Call<List<FollowersResponse>>

    @GET("users/{username}/following")
    fun listFollowing (
        @Path("username") username : String
    ) : Call <List<FollowingResponse>>


}