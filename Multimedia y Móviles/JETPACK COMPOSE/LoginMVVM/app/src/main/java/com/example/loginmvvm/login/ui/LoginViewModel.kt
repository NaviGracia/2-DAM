package com.example.loginmvvm.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    fun onLoginChange(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enabledLogin(email, password)
    }

    private fun enabledLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
        password.length >= 8
}