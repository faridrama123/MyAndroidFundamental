package com.faridrama123.app.data.local

import androidx.lifecycle.LiveData
import com.faridrama123.app.data.local.entity.*
import com.faridrama123.app.data.local.room.GithubDao

class LocalDataSource private constructor(private val githubDao: GithubDao){

    companion object{
        private val instance : LocalDataSource? = null

        fun getInstance (githubDao: GithubDao) : LocalDataSource =
            instance?: synchronized(this){
               return instance?: LocalDataSource(githubDao)
            }


    }

    fun getsearchUser (key: String) : LiveData<List<SearchEntity>> = githubDao.getSearchUser(key)
    fun insertsearchUser (data : List<SearchEntity>) = githubDao.insertSearchUser(data)


    fun getdetailUser (username : String) : LiveData<DetailEntity> = githubDao.getDetailUser(username)
    fun insertdetailUser (data : List<DetailEntity>) = githubDao.insertDetailUser(data)


    fun getfollowingUser () : LiveData<List<FollowingEntity>> = githubDao.getFollowingUser()
    fun insertfollowingUser (data: List<FollowingEntity>) = githubDao.insertFollowingUser(data)
    fun deletefollowingUser () = githubDao.deleteFollowingUser()


    fun getfollowersUser () : LiveData<List<FollowersEntity>> = githubDao.getFollowersUser()
    fun insertfollowersUser (data: List<FollowersEntity>) = githubDao.insertFollowersUser(data)
    fun deletefollowerUser () = githubDao.deleteFollowersgUser()




    fun setFavoriteUser (data : SearchEntity, state : Boolean) {
        data.isfavorite = state
        githubDao.setFavoriteUser(data)
    }
    fun getFavoriteUser () : LiveData<List<SearchEntity>> = githubDao.getFavoriteUser()




}