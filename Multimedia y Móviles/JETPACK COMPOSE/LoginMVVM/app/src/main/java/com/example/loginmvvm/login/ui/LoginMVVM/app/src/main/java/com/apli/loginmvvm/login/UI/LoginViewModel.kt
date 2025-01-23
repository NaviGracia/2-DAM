package com.apli.loginmvvm.login.UI

import android.util.Patterns
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled


    fun onEmailChange(email:String){
         _email.value = email

    }

    fun onPasswordChange(password:String){
        _password.value = password


    }

    fun onLoginChange(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = enableLogin(email,password)



    }

    private fun enableLogin(email:String, password:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8





    }


}


