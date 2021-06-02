package com.faridrama123.consumerapp

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.consumerapp.databinding.ActivityMainBinding
import com.faridrama123.consumerapp.entity.SearchEntity
import com.faridrama123.consumerapp.ui.SearchAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    private lateinit var searchAdapter : SearchAdapter
    private lateinit var binding : ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchAdapter = SearchAdapter()
        fetchContent()

        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchAdapter
        }
    }

    private fun fetchContent() {

        val AUTHORITY = "com.faridrama123.app.provider"
        val TABLE_NAME = "searchuser"

        val uri : Uri = Uri.Builder()
            .scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()


        GlobalScope.launch(Dispatchers.Main) {

            val deferredNotes = async(Dispatchers.IO) {
                val cursor = contentResolver.query(uri, null, null, null, null)
                mapCursorToArrayList(cursor)
            }
            val notes = deferredNotes.await()

            if (notes!= null) {
                searchAdapter.setData(notes)
            }

        }
    }


    fun mapCursorToArrayList(notesCursor: Cursor?): java.util.ArrayList<SearchEntity> {
        val notesList = java.util.ArrayList<SearchEntity>()

        notesCursor?.apply {
            while (moveToNext()) {
                val gistsUrl= getString(getColumnIndexOrThrow("gistsUrl"))
                val reposUrl= getString(getColumnIndexOrThrow("reposUrl"))
                val followingUrl= getString(getColumnIndexOrThrow("followingUrl"))
                val starredUrl= getString(getColumnIndexOrThrow("starredUrl"))
                val login= getString(getColumnIndexOrThrow("login"))
                val followersUrl= getString(getColumnIndexOrThrow("followersUrl"))
                val type= getString(getColumnIndexOrThrow("type"))
                val url= getString(getColumnIndexOrThrow("url"))
                val subscriptionsUrl= getString(getColumnIndexOrThrow("subscriptionsUrl"))
                val score= getDouble(getColumnIndexOrThrow("score"))
                val receivedEventsUrl= getString(getColumnIndexOrThrow("receivedEventsUrl"))
                val avatarUrl= getString(getColumnIndexOrThrow("avatarUrl"))
                val eventsUrl= getString(getColumnIndexOrThrow("eventsUrl"))
                val htmlUrl= getString(getColumnIndexOrThrow("htmlUrl"))
                val siteAdmin= getString(getColumnIndexOrThrow("siteAdmin"))
                val id = getInt(getColumnIndexOrThrow("id"))
                val gravatarId= getString(getColumnIndexOrThrow("gravatarId"))
                val nodeId= getString(getColumnIndexOrThrow("nodeId"))
                val organizationsUrl= getString(getColumnIndexOrThrow("organizationsUrl"))
                val key= getString(getColumnIndexOrThrow("key"))
                val isfavorite= getString(getColumnIndexOrThrow("isfavorite"))



                notesList.add(
                    SearchEntity(gistsUrl, reposUrl, followingUrl, starredUrl,login,followersUrl,
                    type,url,subscriptionsUrl,score,receivedEventsUrl,avatarUrl,eventsUrl,htmlUrl,siteAdmin.toBoolean(),id,gravatarId
                    ,nodeId,organizationsUrl,key,isfavorite.toBoolean()
                )
                )
            }
        }
        return notesList
    }
}