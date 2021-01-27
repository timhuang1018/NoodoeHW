package com.nextv.noodoehw.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.nextv.noodoehw.config.Navigate
import com.nextv.noodoehw.helper.EventWrapper
import com.nextv.noodoehw.helper.Result
import com.nextv.noodoehw.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by TimHuang on 2021/1/25.
 */
class FirstViewModel(private val repository: UserRepository):ViewModel() {


    private val _message = MutableLiveData<EventWrapper<String>>()
    val message : LiveData<EventWrapper<String>>
        get() = _message

    private val _emailError = MutableLiveData<EventWrapper<String>>()
    val emailError : LiveData<EventWrapper<String>>
    get() = _emailError

    private val _passwordError = MutableLiveData<EventWrapper<String>>()
    val passwordError : LiveData<EventWrapper<String>>
        get() = _passwordError

    private val _navigation = MutableLiveData<EventWrapper<Navigate>>()
    val navigation : LiveData<EventWrapper<Navigate>>
        get() = _navigation

    val loading = MutableLiveData<Boolean>()

    /*
    test2@qq.com
    test1234qq
     */
    fun login(email: String, password: String) {
        if (email.isEmpty()){
            _emailError.value = EventWrapper("不能為空值")
            return
        }
        if (!email.contains("@")){
            _emailError.value = EventWrapper("格式有誤")
            return
        }
        if (password.isEmpty()){
            _passwordError.value = EventWrapper("不能為空值")
            return
        }
        loading.value = true

        viewModelScope.launch (Dispatchers.Main){
            when(repository.login(email,password)){
                is Result.Success->{
                    Log.e("FirstViewModel","login Success")
                    _navigation.value = EventWrapper(Navigate.ToSecond)
                }
                Result.Failure->{
                    _message.value = EventWrapper("登入失敗，請檢查帳號密碼")
                }
            }
            loading.value = false
        }
    }

}

@Suppress("UNCHECKED_CAST")
class FirstViewModelFactory(private val repository: UserRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FirstViewModel(repository) as T
    }
}
