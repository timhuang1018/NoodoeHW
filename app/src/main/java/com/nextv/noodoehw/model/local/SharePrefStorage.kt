package com.nextv.noodoehw.model.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.nextv.noodoehw.domain.data.UserRemote

/**
 * Created by timhuang on 2021/1/27.
 **/
const val NORMAL_LEVEL = "normal level"
const val EMAIL = "email"
const val TOKEN = "token"
class SharePrefStorage(context: Context):Storage {

    private val sharedPreferences:SharedPreferences = context.getSharedPreferences(NORMAL_LEVEL,Activity.MODE_PRIVATE)

    override fun saveData(data: UserRemote) {
        sharedPreferences.edit {
            putString(EMAIL,data.username)
            putString(TOKEN,data.sessionToken)
        }
    }

    override fun getUserEmail(): String {
        return sharedPreferences.getString(EMAIL,"")?:""
    }

}