package com.nextv.noodoehw.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.nextv.noodoehw.domain.data.Info
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.TrafficRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2021/1/26.
 **/

class SecondViewModel(private val repository: TrafficRepository): ViewModel() {

    private val _data = MutableLiveData<List<Info>>()
    val data : LiveData<List<Info>>
        get() = _data

    private val _message = MutableLiveData<EventWrapper<String>>()
    val message : LiveData<EventWrapper<String>>
        get() = _message


    fun getData(){
        viewModelScope.launch (Dispatchers.Main){
            when(val result = repository.getTrafficData()){
                is Result.Success->{
                    Log.e("FirstViewModel","login Success")
                    _data.value = result.value
                }
                Result.Failure->{
                    _message.value = EventWrapper("伺服器錯誤")
                }
            }
        }
    }
}

class SecondViewModelFactory(private val repository: TrafficRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SecondViewModel(repository) as T
    }
}