package com.nextv.noodoehw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.model.UserRepository

/**
 * Created by TimHuang on 2021/1/25.
 */
class FirstViewModel(private val repository: UserRepository):ViewModel() {

    val message: LiveData<EventWrapper<String>> = repository.message

    fun login(email: String, password: String) {
        //TODO login logic
        repository.login(email,password)
    }

}

class FirstViewModelFactory(private val repository: UserRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FirstViewModel(repository) as T
    }
}
