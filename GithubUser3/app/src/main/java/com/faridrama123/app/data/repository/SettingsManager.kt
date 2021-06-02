package com.faridrama123.app.data.repository

import android.content.Context
import android.content.SharedPreferences

class SettingsManager (context: Context){
    companion object {
        const val KEY_REMINDER = "reminder"
    }


    private var pref: SharedPreferences = context.getSharedPreferences("Session", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = pref.edit()

    fun save() {
        editor.putBoolean(KEY_REMINDER, true)
            .commit()
    }

    fun delete() {
        editor.putBoolean(KEY_REMINDER, false)
            .commit()
    }

    fun getFromPreference() : Boolean = pref.getBoolean(KEY_REMINDER, false)


}