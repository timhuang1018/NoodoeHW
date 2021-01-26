package com.nextv.noodoehw.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nextv.noodoehw.domain.User
import com.nextv.noodoehw.domain.mapper.asUser
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.remote.RemoteApi

/**
 * Created by TimHuang on 2021/1/25.
 * processing data related to user
 */
class UserRepository(private val apiInstance:RemoteApi){


    suspend fun login(username:String, password:String):Result<User>{
        return try {

            val response = apiInstance.login(username = username,password = password)

            Result.Success(response.asUser())
        }catch (e:Exception){
            Log.e("UserRepository","login,$e")
            //not doing error handling in specific
            Result.Failure
        }
    }
}