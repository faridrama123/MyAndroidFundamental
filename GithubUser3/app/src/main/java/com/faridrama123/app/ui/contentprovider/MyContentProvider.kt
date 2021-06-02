package com.faridrama123.app.ui.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.faridrama123.app.data.local.room.GithubDatabase
import com.faridrama123.app.ui.contentprovider.DatabaseContract.AUTHORITY
import com.faridrama123.app.ui.contentprovider.DatabaseContract.NoteColumns.Companion.TABLE_NAME

class MyContentProvider : ContentProvider() {

    companion object {


        private const val NOTE = 1
        private const val NOTE_ID = 2
        private lateinit var mgithubDatabase: GithubDatabase

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE)
            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", NOTE_ID)
        }
    }

    override fun onCreate(): Boolean {
        mgithubDatabase = GithubDatabase.getInstance(context as Context)
        return true
    }

    override fun query(uri: Uri, strings: Array<String>?, s: String?, strings1: Array<String>?, s1: String?): Cursor? {

        val sampleDao = mgithubDatabase.githubDao()

        return when (sUriMatcher.match(uri)) {

            NOTE -> sampleDao.cursorgetFavUser()
            else -> null
        }
    }


    override fun getType(uri: Uri): String? {
        return null
    }


    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }


    override fun update(uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        throw UnsupportedOperationException()
    }
}