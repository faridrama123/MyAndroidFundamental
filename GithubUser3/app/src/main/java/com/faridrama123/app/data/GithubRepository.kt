package com.faridrama123.app.data

import androidx.lifecycle.LiveData
import com.faridrama123.app.data.local.LocalDataSource
import com.faridrama123.app.data.local.entity.*
import com.faridrama123.app.data.remote.RemoteDataSource
import com.faridrama123.app.data.remote.network.ApiResponse
import com.faridrama123.app.data.remote.response.DetailResponse
import com.faridrama123.app.data.remote.response.FollowersResponse
import com.faridrama123.app.data.remote.response.FollowingResponse
import com.faridrama123.app.data.remote.response.SearchResponseItems
import com.faridrama123.app.utils.AppExecutors
import com.faridrama123.app.vo.Resource

class GithubRepository private constructor(
    private  val remoteDataSource: RemoteDataSource,
    private  val localDataSource: LocalDataSource,
    private  val appExecutors: AppExecutors
)
    : GithubDataSource

{

    companion object{

        @Volatile
        private var instance : GithubRepository? = null

        fun getInstance (remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource,appExecutors: AppExecutors)
        : GithubRepository =
            instance?: synchronized(this){
                instance?: GithubRepository(remoteDataSource,localDataSource,appExecutors)
            }


    }

    override fun searchUser(key: String): LiveData<Resource<List<SearchEntity>>> =
        object : NetworkBoundResource <List<SearchEntity>, List<SearchResponseItems>> (appExecutors) {
            override fun loadFromDB(): LiveData<List<SearchEntity>> {
               return localDataSource.getsearchUser(key)
            }

            override fun shouldFetch(data: List<SearchEntity>?): Boolean {
               return true
            }

            override fun createCall(): LiveData<ApiResponse<List<SearchResponseItems>>> {
               return remoteDataSource.searchUser(key)
            }

            override fun saveCallResult(data: List<SearchResponseItems>) {
                val datalist = ArrayList<SearchEntity>()
                for (response in data){
                    val item = SearchEntity(
                        response.gistsUrl, response.reposUrl, response.followingUrl,
                        response.starredUrl, response.login, response.followersUrl,
                        response.type, response.url, response.subscriptionsUrl,
                        response.score, response.receivedEventsUrl, response.avatarUrl,
                        response.eventsUrl, response.htmlUrl, response.siteAdmin,response.id,
                        response.gravatarId, response.nodeId, response.organizationsUrl, key , false
                    )

                    datalist.add(item)

                }
                localDataSource.insertsearchUser(datalist)
            }

        }.asLiveData()



    override fun detailUser(username: String): LiveData<Resource<DetailEntity>> {

        return object : NetworkBoundResource<DetailEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> {
                return localDataSource.getdetailUser(username)
            }

            override fun shouldFetch(data: DetailEntity?): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> {
                return remoteDataSource.detailUser(username)
            }

            override fun saveCallResult(data: DetailResponse) {

                val datalist = ArrayList<DetailEntity>()

                val item = DetailEntity(
                    data.gistsUrl,
                    data.reposUrl,
                    data.followingUrl,
                    data.twitterUsername,
                    data.bio,
                    data.createdAt,
                    data.login,
                    data.type,
                    data.blog,
                    data.subscriptionsUrl,
                    data.updatedAt,
                    data.siteAdmin,
                    data.company,
                    data.id,
                    data.publicRepos,
                    data.gravatarId,
                    data.email,
                    data.organizationsUrl,
                    data.hireable,
                    data.starredUrl,
                    data.followersUrl,
                    data.publicGists,
                    data.url,
                    data.receivedEventsUrl,
                    data.followers,
                    data.avatarUrl,
                    data.eventsUrl,
                    data.htmlUrl,
                    data.following,
                    data.name,
                    data.location,
                    data.nodeId
                )

                datalist.add(item)
                return localDataSource.insertdetailUser(datalist)

            }


        }.asLiveData()
    }

    override fun followingUser(username: String): LiveData<Resource<List<FollowingEntity>>> =
            object : NetworkBoundResource <List<FollowingEntity>, List<FollowingResponse>> (appExecutors) {
                override fun loadFromDB(): LiveData<List<FollowingEntity>> {
                    return localDataSource.getfollowingUser()
                }

                override fun shouldFetch(data: List<FollowingEntity>?): Boolean {
                    return  true
                }

                override fun createCall(): LiveData<ApiResponse<List<FollowingResponse>>> {
                    return remoteDataSource.followingUser(username)
                }

                override fun saveCallResult(data: List<FollowingResponse>) {
                    val datalist = ArrayList<FollowingEntity>()
                    for (response in data){
                        val item = FollowingEntity(
                                response.login, response.id, response.nodeId,
                                response.avatarUrl
                        )

                        datalist.add(item)

                    }
                    localDataSource.deletefollowingUser()

                    localDataSource.insertfollowingUser(datalist)
                }

            }.asLiveData()

    override fun followersUser(username: String): LiveData<Resource<List<FollowersEntity>>> =
            object : NetworkBoundResource <List<FollowersEntity>, List<FollowersResponse>> (appExecutors) {
                override fun loadFromDB(): LiveData<List<FollowersEntity>> {
                    return localDataSource.getfollowersUser()
                }

                override fun shouldFetch(data: List<FollowersEntity>?): Boolean {
                    return  true
                }

                override fun createCall(): LiveData<ApiResponse<List<FollowersResponse>>> {
                    return remoteDataSource.followersUser(username)
                }

                override fun saveCallResult(data: List<FollowersResponse>) {
                    val datalist = ArrayList<FollowersEntity>()
                    for (response in data){
                        val item = FollowersEntity(
                                response.login, response.id, response.nodeId,
                                response.avatarUrl
                        )
                        datalist.add(item)
                    }
                    localDataSource.deletefollowerUser()

                    localDataSource.insertfollowersUser(datalist)
                }

            }.asLiveData()

    override fun setFavoriteUser(data: SearchEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteUser(data, state)
        }
    }

    override fun getFavoriteUser(): LiveData<List<SearchEntity>> {
        return localDataSource.getFavoriteUser()
    }


}