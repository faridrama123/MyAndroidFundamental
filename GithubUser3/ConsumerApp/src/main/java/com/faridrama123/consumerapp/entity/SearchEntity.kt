package com.faridrama123.consumerapp.entity

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchEntity (

	val gistsUrl: String,

	val reposUrl: String,

	val followingUrl: String,

	val starredUrl: String,

	val login: String,

	val followersUrl: String,

	val type: String,

	val url: String,

	val subscriptionsUrl: String,

	val score: Double,

	val receivedEventsUrl: String,

	val avatarUrl: String,

	val eventsUrl: String,

	val htmlUrl: String,

	val siteAdmin: Boolean,

	val id: Int,

	val gravatarId: String,

	val nodeId: String,

	val organizationsUrl: String,

    val key: String,



	var isfavorite: Boolean
): Parcelable
