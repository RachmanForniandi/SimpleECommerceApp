package com.example.simpleecommerceapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleecommerceapp.models.login.ResponseLogin
import com.example.simpleecommerceapp.repository.RepositoryUser

class LoginViewModel : ViewModel() {
    private val repositoryUser = RepositoryUser()

    //live data
    var responseLogin = MutableLiveData<ResponseLogin>()

    var apiError = MutableLiveData<Throwable>()

    var isLoading = MutableLiveData<Boolean>()

    var isEmpty = MutableLiveData<Boolean>()



    fun loginUser(username: String, password: String){
        isLoading.value = true
        if (username.isNotEmpty()&&password.isNotEmpty()){
            //success
            repositoryUser.loginCommand(username, password,
                {
                    responseLogin.value = it
                    isLoading.value = false
                }){
                    apiError.value = it
                    isLoading.value = false
                }
        }else{
            //error
            isEmpty.value = true
            isLoading.value = false
        }
    }
}
