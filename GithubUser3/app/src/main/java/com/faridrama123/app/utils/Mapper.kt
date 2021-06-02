package com.faridrama123.app.utils

import android.database.Cursor
import com.faridrama123.app.data.local.entity.SearchEntity

object Mapper {

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



                notesList.add(SearchEntity(gistsUrl, reposUrl, followingUrl, starredUrl,login,followersUrl,
                        type,url,subscriptionsUrl,score,receivedEventsUrl,avatarUrl,eventsUrl,htmlUrl,siteAdmin.toBoolean(),id,gravatarId
                        ,nodeId,organizationsUrl,key,isfavorite.toBoolean()
                ))
            }
        }
        return notesList
    }

}