package com.faridrama123.app.data.repository

import android.content.Context
import android.content.SharedPreferences

class SettingsRepo (private  val settingsManager: SettingsManager){

    companion object {
        @Volatile
        private var instance: SettingsRepo? = null

        fun getInstance(settingsManager: SettingsManager): SettingsRepo =
            instance ?: synchronized(this) {
                instance ?: SettingsRepo(settingsManager)
            }
    }


    fun save() = settingsManager.save()

    fun delete() = settingsManager.delete()

    fun getFromPreference() : Boolean = settingsManager.getFromPreference()
}