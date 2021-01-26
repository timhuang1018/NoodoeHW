package com.nextv.noodoehw.model

import androidx.lifecycle.MutableLiveData
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.model.remote.RemoteApi

/**
 * Created by TimHuang on 2021/1/25.
 * processing data related to user
 */
class UserRepository(private val apiInstance:RemoteApi){

    val message =  MutableLiveData<EventWrapper<String>>()

    fun login(username:String,password:String){
        apiInstance.login(username = username,password = password)
    }
}