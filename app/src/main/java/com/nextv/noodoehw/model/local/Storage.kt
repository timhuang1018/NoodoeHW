package com.nextv.noodoehw.model.local

import com.nextv.noodoehw.domain.data.UserRemote

/**
 * Created by timhuang on 2021/1/27.
 **/
interface Storage {
    fun saveData(data: UserRemote)
    fun getUserEmail(): String
}