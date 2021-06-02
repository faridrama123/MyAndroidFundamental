package com.faridrama123.app.data.remote.response

import com.google.gson.annotations.SerializedName


data class FollowingResponse(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("node_id")
	val nodeId: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

)
