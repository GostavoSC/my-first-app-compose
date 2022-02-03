package com.gstv.primeirocompose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gstv.primeirocompose.model.UserModel

class LoginActivityViewModel : ViewModel() {

    private val _sendLoginLiveData: MutableLiveData<UserModel> = MutableLiveData()
    val sendLoginLiveData: LiveData<UserModel> = _sendLoginLiveData

    fun sendLogin(email: String, password: String) {
        if (email == "admin" && password == "teste123") {
            _sendLoginLiveData.postValue(
                UserModel(
                    name = "Gustavo",
                    email = email,
                    password = password
                )
            )
        }
    }

}