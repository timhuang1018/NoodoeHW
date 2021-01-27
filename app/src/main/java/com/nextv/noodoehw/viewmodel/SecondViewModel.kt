package com.nextv.noodoehw.viewmodel

import androidx.lifecycle.*
import com.nextv.noodoehw.domain.TrafficUI
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.TrafficRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2021/1/26.
 **/

class SecondViewModel(private val repository: TrafficRepository): ViewModel() {

    private val _data = MutableLiveData<List<TrafficUI>>()
    val data : LiveData<List<TrafficUI>>
        get() = _data

    private val _message = MutableLiveData<EventWrapper<String>>()
    val message : LiveData<EventWrapper<String>>
        get() = _message

    val loading = MutableLiveData<Boolean>()


    fun getData(){
        viewModelScope.launch (Dispatchers.Main){
            when(val result = repository.getTrafficData()){
                is Result.Success->{
                    _data.value = result.value
                }
                Result.Failure->{
                    _message.value = EventWrapper("伺服器錯誤")
                }
            }
            loading.value = false
        }
    }
}

class SecondViewModelFactory(private val repository: TrafficRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SecondViewModel(repository) as T
    }
}