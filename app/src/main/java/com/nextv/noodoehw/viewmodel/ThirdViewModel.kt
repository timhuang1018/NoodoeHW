package com.nextv.noodoehw.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.nextv.noodoehw.config.Navigate
import com.nextv.noodoehw.domain.TrafficUI
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2021/1/27.
 **/

class ThirdViewModel (private val repository: UserRepository): ViewModel() {



    private val _email = MutableLiveData<String>()
    val email : LiveData<String>
        get() = _email


    private val _message = MutableLiveData<EventWrapper<String>>()
    val message : LiveData<EventWrapper<String>>
        get() = _message

    val loading = MutableLiveData<Boolean>()


    fun getData() {
        _email.value = repository.getUserEmail()
    }

}

class ThirdViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ThirdViewModel(repository) as T
    }
}
