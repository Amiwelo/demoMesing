package com.example.demomesing.features.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation

class LoginViewModel(private val dataSource: LoginDataSource): ViewModel() {


    fun signInService(email: String, pwd: String): Int{
        var estate= -1

        Log.i("Info", "SignInServiceViewModel")
        dataSource.signIn(email, pwd, object : ObjectOperation {
            override fun onSucces(obj: Any?) {
                Log.i("obj ", "$obj")
            }

            override fun onError(obj: Any?) {
                Log.e("Error ", "$obj")
            }
        })
        return estate
    }

}