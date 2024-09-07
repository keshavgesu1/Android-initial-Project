package com.example.smarttask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pravasiuttrakhandi.base.FieldErrors
import com.app.pravasiuttrakhandi.repository.RegisterRepository
import com.example.smarttask.model.BaseResponse
import com.example.smarttask.model.UserDetails
import com.example.smarttask.networking.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class InitialViewModel(private val dataRepository: RegisterRepository) : ViewModel() {

    var registerUserData = MutableLiveData<UserDetails>()
    var errorFields = MutableLiveData<FieldErrors>()

    private var _loginResponseLiveData = MutableLiveData<DataResult<BaseResponse>>()
    var loginResponseData: LiveData<DataResult<BaseResponse>> = _loginResponseLiveData

    init {
        registerUserData.value = UserDetails()
        errorFields.value = FieldErrors()

    }

    fun sendOTP(): LiveData<DataResult<BaseResponse>> {
        // data is valid proceed to next flow, hit API here
        viewModelScope.launch {
            val response = dataRepository.login(registerUserData.value!!)
            withContext(Dispatchers.Main) {

                response.collect {
                    _loginResponseLiveData.postValue(it)
                }
            }
        }
        return loginResponseData
    }


    fun registerUser(): MutableLiveData<DataResult<BaseResponse>> {
        val _responseLiveData = MutableLiveData<DataResult<BaseResponse>>()
//        if (isValidated!!) {
        viewModelScope.launch {
            val response = dataRepository.resgisterUser(registerUserData.value!!)
            withContext(Dispatchers.Main) {

                response.collect {
                    _responseLiveData.postValue(it)
                }
            }
        }
//        }
        return _responseLiveData
    }
}