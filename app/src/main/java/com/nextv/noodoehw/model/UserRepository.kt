package com.nextv.noodoehw.model

import android.util.Log
import com.nextv.noodoehw.domain.UserUI
import com.nextv.noodoehw.domain.mapper.asUser
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.local.Storage
import com.nextv.noodoehw.model.remote.RemoteApi

/**
 * Created by TimHuang on 2021/1/25.
 * processing data related to user
 */
class UserRepository(
        private val apiInstance:RemoteApi,
        private val storage:Storage
){


    suspend fun login(username:String, password:String):Result<UserUI>{
        return try {

            val response = apiInstance.login(username = username,password = password)
            storage.saveData(data = response)
            Result.Success(response.asUser())
        }catch (e:Exception){
            Log.e("UserRepository","login,$e")
            //not doing error handling in specific
            Result.Failure
        }
    }

    fun getUserEmail(): String {
        val email = storage.getUserEmail()
        return "Email: $email"
    }
}