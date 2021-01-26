package com.nextv.noodoehw.model

import android.util.Log
import com.nextv.noodoehw.domain.data.Info
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.remote.RemoteApi

/**
 * Created by timhuang on 2021/1/26.
 **/

class TrafficRepository(private val apiInstance: RemoteApi) {

    suspend fun getTrafficData(): Result<List<Info>> {
        return try {

            val response = apiInstance.getTrafficData()

            Result.Success(response.News)
        }catch (e:Exception){
            Log.e("UserRepository","login,$e")
            //not doing error handling in specific
            Result.Failure
        }

    }
}