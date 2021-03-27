package com.faridrama123.githubuser2

import com.faridrama123.githubuser2.response.DetailResponse
import com.faridrama123.githubuser2.response.FollowersResponse
import com.faridrama123.githubuser2.response.FollowingResponse
import com.faridrama123.githubuser2.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUser (
        @Query("q") q : String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun detailUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun listFollower(
        @Path("username") username: String
    ): Call<FollowersResponse>

    @GET("users/{username}/following")
    fun listFollowing(
        @Path("username") username: String
    ): Call <FollowingResponse>


}