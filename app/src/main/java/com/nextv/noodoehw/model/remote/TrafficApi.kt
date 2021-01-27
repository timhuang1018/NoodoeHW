package com.nextv.noodoehw.model.remote

import com.nextv.noodoehw.BuildConfig
import com.nextv.noodoehw.domain.data.InfoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by timhuang on 2021/1/27.
 * extract method for traffic
 **/
interface TrafficApi {
    companion object{
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

        val trafficApi : TrafficApi by lazy {
            val retrofit = Retrofit
                    .Builder()
                    .client(client)
                    .baseUrl(TRAFFIC_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit.create(TrafficApi::class.java)
        }
    }

    @GET("/dotapp/news.json")
    suspend fun getTrafficData():InfoResponse
}