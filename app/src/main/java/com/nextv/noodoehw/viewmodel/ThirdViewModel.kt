package com.nextv.noodoehw.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.nextv.noodoehw.domain.UserUI
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.UserRepository
import kotlinx.coroutines.launch

/**
 * Created by timhuang on 2021/1/27.
 **/

class ThirdViewModel (private val repository: UserRepository): ViewModel() {



    private val _user = MutableLiveData<UserUI>()
    val user : LiveData<UserUI>
        get() = _user


    private val _message = MutableLiveData<EventWrapper<String>>()
    val message : LiveData<EventWrapper<String>>
        get() = _message

    private val timezones = mutableListOf<String>()

    init {
        //spec did not specify limit, so bottom and upper limit here are not important
        for (i in 1..999){
            timezones.add(i.toString())
        }
    }

    val loading = MutableLiveData<Boolean>()


    fun getData() {
        _user.value = repository.getUser()
    }

    fun changeTimezone(position:Int){
        val timezone = timezones[position].toInt()
        loading.value = true
        viewModelScope.launch {
            when(val result = repository.updateTimeZone(timezone)){
                is Result.Success->{
                    _message.value = EventWrapper("更新成功！")
                }
                Result.Failure->{
                    getData()
                    _message.value = EventWrapper("更新失敗，請稍後再試")
                }
            }

            loading.value = false
        }
    }

    fun getTimeZoneList(): List<String> {
        return timezones
    }

}

class ThirdViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ThirdViewModel(repository) as T
    }
}
