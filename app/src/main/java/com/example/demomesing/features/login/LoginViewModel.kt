package com.example.demomesing.features.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.model.User
import com.google.gson.Gson

class LoginViewModel(private val dataSource: LoginDataSource): ViewModel() {

    private val _responseBody = MutableLiveData<User>()
    val responseBody: LiveData<User> = _responseBody

    fun signInService(email: String, pwd: String): Int{
        var estate= -1
        Log.i("Info", "SignInServiceViewModel")
        dataSource.signIn(email, pwd, object : ObjectOperation {
            override fun onSucces(obj: Any?) {
                Log.i("obj ", "$obj")
                _responseBody.value = obj as User?
            }

            override fun onError(obj: Any?) {
                Log.e("Error ", "$obj")
            }
        })
        return estate
    }

}