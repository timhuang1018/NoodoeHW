package com.nextv.noodoehw.di

import android.content.Context
import com.nextv.noodoehw.model.TrafficRepository
import com.nextv.noodoehw.model.UserRepository
import com.nextv.noodoehw.model.local.SharePrefStorage
import com.nextv.noodoehw.model.remote.TrafficApi
import com.nextv.noodoehw.model.remote.UserApi
import com.nextv.noodoehw.viewmodel.FirstViewModelFactory
import com.nextv.noodoehw.viewmodel.SecondViewModelFactory
import com.nextv.noodoehw.viewmodel.ThirdViewModelFactory

/**
 * Created by timhuang on 2021/1/25.
 * inject dependency using service locator pattern
 * decide which instance of implementation should be injected
 **/
object DependencyProvider {
    fun getFirstViewModelFactory(context: Context): FirstViewModelFactory {
        return FirstViewModelFactory(UserRepository(apiInstance = UserApi.userApi,storage = SharePrefStorage(context)))
    }

    fun getSecondViewModelFactory(): SecondViewModelFactory {
        return SecondViewModelFactory(TrafficRepository(apiInstance = TrafficApi.trafficApi))
    }

    fun getThirdViewModelFactory(context: Context): ThirdViewModelFactory {
        return ThirdViewModelFactory(UserRepository(apiInstance = UserApi.userApi,storage = SharePrefStorage(context)))

    }


}