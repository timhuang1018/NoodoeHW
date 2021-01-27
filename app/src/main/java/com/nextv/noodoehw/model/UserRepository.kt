package com.nextv.noodoehw.model

import android.util.Log
import com.nextv.noodoehw.domain.UserUI
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.helper.createJsonRequestBody
import com.nextv.noodoehw.model.local.Storage
import com.nextv.noodoehw.model.local.TIME_ZONE
import com.nextv.noodoehw.model.remote.UserApi

/**
 * Created by TimHuang on 2021/1/25.
 * processing data related to user
 */
class UserRepository(
        private val apiInstance:UserApi,
        private val storage:Storage
){


    suspend fun login(username:String, password:String):Result<Any>{
        return try {

            val response = apiInstance.login(username = username,password = password)
            storage.saveData(data = response)
            Result.Success(Any())
        }catch (e:Exception){
            Log.e("UserRepository","login,$e")
            //not doing error handling in specific
            Result.Failure
        }
    }

    fun getUser(): UserUI {
        return storage.getUser()
    }

    suspend fun updateTimeZone(value: Int):Result<String>{
        return try {
            val token = storage.getToken()
            val userName = storage.getObjectId()
            val response = apiInstance.updateUser(userId = userName,session = token,body = createJsonRequestBody(TIME_ZONE to value))
            storage.updateTimezone(value)
            Result.Success(response.updatedAt)
        }catch (e:Exception){
            Result.Failure
        }

    }
}