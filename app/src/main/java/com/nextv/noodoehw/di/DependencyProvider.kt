package com.nextv.noodoehw.di

import com.nextv.noodoehw.model.UserRepository
import com.nextv.noodoehw.model.remote.RemoteApi
import com.nextv.noodoehw.viewmodel.FirstViewModelFactory

/**
 * Created by timhuang on 2021/1/25.
 * inject dependency using service locator pattern
 **/
object DependencyProvider {
    fun getFirstViewModelFactory(): FirstViewModelFactory {
        return FirstViewModelFactory(UserRepository(apiInstance = RemoteApi.userApi))
    }


}