package com.example.demomesing.features.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.model.User

class LoginViewModel(private val dataSource: LoginDataSource, private val shPreference: ShPreference): ViewModel() {

    private val _responseBody = MutableLiveData<User>()
    val responseBody: LiveData<User> = _responseBody

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    fun signInService(email: String, pwd: String){
        dataSource.signIn(email, pwd, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                Log.i("obj ", "$obj")
                _responseBody.value = obj as User?
                shPreference.user = obj
            }

            override fun onError(obj: Any?) {
                _message.value = obj as String?
            }
        })
    }

}