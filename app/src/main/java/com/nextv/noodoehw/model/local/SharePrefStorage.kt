package com.nextv.noodoehw.model.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.nextv.noodoehw.domain.UserUI
import com.nextv.noodoehw.domain.data.UserRemote

/**
 * Created by timhuang on 2021/1/27.
 **/
const val NORMAL_LEVEL = "normal level"
const val EMAIL = "email"
const val TOKEN = "token"
const val OBJECT_ID = "object id"
const val TIME_ZONE = "timezone"
class SharePrefStorage(context: Context):Storage {

    private val sharedPreferences:SharedPreferences = context.getSharedPreferences(NORMAL_LEVEL,Activity.MODE_PRIVATE)

    override fun saveData(data: UserRemote) {
        sharedPreferences.edit {
            putString(EMAIL,data.username)
            putString(TOKEN,data.sessionToken)
            putString(OBJECT_ID,data.objectId)
            putInt(TIME_ZONE,data.timezone)
        }
    }

    override fun getUser(): UserUI {
        return UserUI(
                (sharedPreferences.getString(EMAIL,"")?:"").let { "Email: $it" },
                sharedPreferences.getInt(TIME_ZONE,-1)
        )
    }

    override fun getToken(): String {
        return sharedPreferences.getString(TOKEN,"")?:""
    }

    override fun getObjectId(): String {
        return sharedPreferences.getString(OBJECT_ID,"") ?:""
    }

    override fun updateTimezone(timezone: Int) {
        sharedPreferences.edit(commit = true){
            putInt(TIME_ZONE,timezone)
        }
    }

}