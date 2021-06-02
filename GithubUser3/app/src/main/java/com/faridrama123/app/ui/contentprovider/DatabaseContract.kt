package com.faridrama123.app.ui.contentprovider

import android.net.Uri
import android.provider.BaseColumns


object DatabaseContract {


    const val AUTHORITY = "com.faridrama123.app.provider"
    const val SCHEME = "content"

    class NoteColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "searchuser"


            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }

    }
}
