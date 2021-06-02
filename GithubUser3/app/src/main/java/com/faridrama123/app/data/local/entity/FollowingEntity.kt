package com.faridrama123.app.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "following")

data class FollowingEntity(

		@PrimaryKey
		@NonNull
		@ColumnInfo(name = "login")
		val login: String,

		@ColumnInfo(name = "id")
		val id: Int,

		@ColumnInfo(name = "nodeId")
		val nodeId: String,

		@ColumnInfo(name = "avatarUrl")
		val avatarUrl: String,

)
