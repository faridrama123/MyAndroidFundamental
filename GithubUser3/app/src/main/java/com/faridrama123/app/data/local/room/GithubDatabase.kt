package com.faridrama123.app.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faridrama123.app.data.local.entity.*


@Database(entities = [
    SearchEntity::class ,
    DetailEntity::class,
    FollowersEntity::class,
    FollowingEntity::class
  ], version = 3, exportSchema = false)

abstract  class GithubDatabase   () : RoomDatabase() {

    abstract fun githubDao () : GithubDao


    companion object{

        @Volatile
        private  var instance : GithubDatabase? = null

        fun getInstance(context: Context) : GithubDatabase =
            instance?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    GithubDatabase::class.java,
                    "github.db"
                ).build().apply {
                    instance = this
                }
            }
    }





}