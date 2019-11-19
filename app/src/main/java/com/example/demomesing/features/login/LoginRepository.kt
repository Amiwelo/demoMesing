package com.example.demomesing.features.login

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.model.User
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginRepository : LoginDataSource {
    private lateinit var parameter: MutableMap<String, String>
    private lateinit var callServices: CallServices

    private val gson = Gson()

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
                        if(response.body()!!.codeStatus == 200){
                            var obj = gson.fromJson(response.body()!!.objJson[0], User::class.java)
                            Log.i("Response", "$obj")
                            param.onSuccess(obj)
                        } else {
                            var msj = response.body()!!.message
                            Log.i("Response", "$msj")
                            param.onError(msj)
                        }
                    }
                }
            } catch (e: Exception){
                param.onError(e.printStackTrace())
            }
        }
    }

}