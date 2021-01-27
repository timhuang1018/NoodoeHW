package com.nextv.noodoehw.model.remote

import com.nextv.noodoehw.BuildConfig
import com.nextv.noodoehw.domain.data.InfoResponse
import com.nextv.noodoehw.domain.data.UpdateResponse
import com.nextv.noodoehw.domain.data.UserRemote
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by timhuang on 2021/1/25.
 **/
//TODO add user and traffic interface
interface RemoteApi {

    companion object{
        private const val MEMBER_BASE = "https://watch-master-staging.herokuapp.com"
        private const val TRAFFIC_BASE = "https://tcgbusfs.blob.core.windows.net"
        val logginInterceptor = HttpLoggingInterceptor()
            .apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .build()
        val userApi : RemoteApi by lazy {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(MEMBER_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RemoteApi::class.java)
        }

        val trafficApi : RemoteApi by lazy {
            val retrofit = Retrofit
                .Builder()
                .client(client)
                .baseUrl(TRAFFIC_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RemoteApi::class.java)
        }
    }

    @GET("/dotapp/news.json")
    suspend fun getTrafficData():InfoResponse

    @Headers("X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @GET("/api/login")
    suspend fun login(@Query("username",encoded = true)username:String, @Query("password",encoded = true)password:String):UserRemote

    @Headers("X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @PUT("/api/users/{userId}")
    suspend fun updateUser(@Path("userId")userId:String, @Header("X-Parse-Session-Token")session:String, @Body body: RequestBody):UpdateResponse



}