package com.gstv.primeirocompose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gstv.primeirocompose.model.Account

class LoginActivityViewModel : ViewModel() {

    private val _sendLoginLiveData: MutableLiveData<Account> = MutableLiveData()
    val sendLoginLiveData: LiveData<Account> = _sendLoginLiveData

    fun sendLogin(email: String, password: String) {


    }

}