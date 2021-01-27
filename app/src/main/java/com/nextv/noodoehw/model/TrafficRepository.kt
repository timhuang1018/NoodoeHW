package com.nextv.noodoehw.model

import android.util.Log
import com.nextv.noodoehw.domain.TrafficUI
import com.nextv.noodoehw.domain.mapper.asUIdata
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.remote.TrafficApi
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by timhuang on 2021/1/26.
 **/

class TrafficRepository(private val apiInstance: TrafficApi) {

    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    suspend fun getTrafficData(): Result<List<TrafficUI>> {
        return try {

            val response = apiInstance.getTrafficData()

            Result.Success(response.News
                    .sortedByDescending { parseDateTime(it.updatetime) }
                    .map { it.asUIdata() })
        }catch (e: Exception){
            Log.e("UserRepository", "login,$e")
            //not doing error handling in specific
            Result.Failure
        }
    }

    private fun parseDateTime(dateString:String): Long {
        return try {
            df.parse(dateString)?.time ?:-1
        }catch (e:ParseException){
            //incase parse fail, no sorting
            -1
        }
    }
}