package com.faridrama123.app.data.local.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.faridrama123.app.data.local.entity.*


@Dao
interface GithubDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchUser(data: List<SearchEntity>)

    @Query("SELECT * FROM searchuser where `key` = :key")
    fun getSearchUser(key : String): LiveData<List<SearchEntity>>

    @Query("SELECT * FROM searchuser where isFavorite = 1")
    fun cursorgetFavUser(): Cursor

    //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailUser(data: List<DetailEntity>)

    @Query("SELECT * FROM detailuser where login = :username")
    fun getDetailUser(username: String): LiveData<DetailEntity>

    //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowingUser(data: List<FollowingEntity>)

    @Query("SELECT * FROM following")
    fun getFollowingUser(): LiveData<List<FollowingEntity>>

    @Query("DELETE FROM following")
    fun deleteFollowingUser()

    //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowersUser(data: List<FollowersEntity>)

    @Query("SELECT * FROM followers")
    fun getFollowersUser(): LiveData<List<FollowersEntity>>
    @Query("DELETE FROM followers")
    fun deleteFollowersgUser()

    //



    @Update
    fun setFavoriteUser(data: SearchEntity)

    @Query("SELECT * FROM searchuser where isFavorite = 1")
    fun getFavoriteUser(): LiveData<List<SearchEntity>>

    //








}