package com.faridrama123.app.di

import android.content.Context
import com.faridrama123.app.data.GithubRepository
import com.faridrama123.app.data.local.LocalDataSource
import com.faridrama123.app.data.local.room.GithubDatabase
import com.faridrama123.app.data.remote.RemoteDataSource
import com.faridrama123.app.data.remote.network.ApiConfig

import com.faridrama123.app.utils.AppExecutors


object Injection {
    fun provideRepository(context: Context): GithubRepository {

        val database = GithubDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.githubDao())
        val appExecutors = AppExecutors()

        return GithubRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
