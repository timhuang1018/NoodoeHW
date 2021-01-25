package com.nextv.noodoehw.model.remote

import com.nextv.noodoehw.domain.data.InfoResponse
import com.nextv.noodoehw.domain.data.UpdateResponse
import com.nextv.noodoehw.domain.data.UserRemote
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by timhuang on 2021/1/25.
 **/
interface RemoteApi {

    companion object{
        private const val MEMBER_BASE = "https://watch-master-staging.herokuapp.com"
        private const val TRAFFIC_BASE = "https://tcgbusfs.blob.core.windows.net"
        val userApi : RemoteApi by lazy {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(MEMBER_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RemoteApi::class.java)
        }

        val trafficApi : RemoteApi by lazy {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(TRAFFIC_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RemoteApi::class.java)
        }
    }

    @GET("/dotapp/news.json")
    fun getTrafficData():InfoResponse

    @GET("/api/login")
    fun login(@Query("username",encoded = true)username:String, @Query("password",encoded = true)password:String, @Body body:RequestBody):UserRemote

    @PUT("/api/users/{userId}")
    fun updateUser(@Path("userId")userId:String,@Body body: RequestBody):UpdateResponse



}