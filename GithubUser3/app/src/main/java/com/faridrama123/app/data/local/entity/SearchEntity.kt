package com.faridrama123.app.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "searchuser")
data class SearchEntity (

	@ColumnInfo(name = "gistsUrl")
	val gistsUrl: String,

	@ColumnInfo(name = "reposUrl")
	val reposUrl: String,

	@ColumnInfo(name = "followingUrl")
	val followingUrl: String,

	@ColumnInfo(name = "starredUrl")
	val starredUrl: String,

	@ColumnInfo(name = "login")
	val login: String,

	@ColumnInfo(name = "followersUrl")
	val followersUrl: String,

	@ColumnInfo(name = "type")
	val type: String,

	@ColumnInfo(name = "url")
	val url: String,

	@ColumnInfo(name = "subscriptionsUrl")
	val subscriptionsUrl: String,

	@ColumnInfo(name = "score")
	val score: Double,

	@ColumnInfo(name = "receivedEventsUrl")
	val receivedEventsUrl: String,

	@ColumnInfo(name = "avatarUrl")
	val avatarUrl: String,

	@ColumnInfo(name = "eventsUrl")
	val eventsUrl: String,

	@ColumnInfo(name = "htmlUrl")
	val htmlUrl: String,

	@ColumnInfo(name = "siteAdmin")
	val siteAdmin: Boolean,

	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "id")
	val id: Int,

	@ColumnInfo(name = "gravatarId")
	val gravatarId: String,

	@ColumnInfo(name = "nodeId")
	val nodeId: String,

	@ColumnInfo(name = "organizationsUrl")
	val organizationsUrl: String,

	@ColumnInfo(name = "key")
    val key: String,


	@ColumnInfo(name = "isfavorite")
	var isfavorite: Boolean
): Parcelable
