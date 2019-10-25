package com.example.demomesing.features.login

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginRepository : LoginDataSource {
    private lateinit var parameter: MutableMap<String, String>
    private lateinit var callServices: CallServices

    override fun signIn(
        email: String,
        pwd: String,
        param: ObjectOperation
    ) {
        parameter = HashMap()
        parameter["Opcion"]="1"
        parameter["LogUsu"]= email
        parameter["PwdUsu"]= pwd

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response = callServices.loggin(parameter)
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        param.onSucces(response.body())
                        Log.i("Response", "${response.body()}")
                    }else{
                        param.onError("Error")
                    }
                }
            } catch (e: Exception){
                param.onError(e.printStackTrace())
            }
        }
    }

}