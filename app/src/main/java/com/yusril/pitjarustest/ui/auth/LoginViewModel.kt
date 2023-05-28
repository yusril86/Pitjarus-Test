package com.yusril.pitjarustest.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusril.pitjarustest.data.api.ApiClient
import com.yusril.pitjarustest.data.api.ApiServices
import com.yusril.pitjarustest.data.model.Login
import com.yusril.pitjarustest.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val mResponseLogin = MutableLiveData<Resource<Login>>()

    fun loginResponse(email: String, passwd: String) {
        mResponseLogin.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                mResponseLogin.postValue(Resource.success(data = ApiClient.API_SERVICE.login(email, passwd)))
            } catch (exception: Exception) {
                mResponseLogin.postValue(Resource.error(exception.toString(), data = null))
            }
        }
    }

    fun getLoginResponse() : LiveData<Resource<Login>> = mResponseLogin
}