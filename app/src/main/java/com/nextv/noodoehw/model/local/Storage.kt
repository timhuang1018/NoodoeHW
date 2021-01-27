package com.nextv.noodoehw.model.local

import com.nextv.noodoehw.domain.UserUI
import com.nextv.noodoehw.domain.data.UserRemote

/**
 * Created by timhuang on 2021/1/27.
 **/
interface Storage {
    fun saveData(data: UserRemote)
    fun getUser(): UserUI
    fun getToken():String
    fun getObjectId(): String
    fun updateTimezone(timezone: Int)
}