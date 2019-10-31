package com.example.demomesing.features.home

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeRepository: HomeDataSource{
    private lateinit var parameter: MutableMap<String, String>
    private lateinit var callServices: CallServices

    override fun launchMain(val1: Int, val2: Int, val3: Int, objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = val1.toString()
        parameter["IdRol"] = val2.toString()
        parameter["IdPer"] = val3.toString()

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response = callServices.lstMain(parameter)
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        Log.i("MAIN", "${response.body()}")
                        objectOperation.onSucces(response.body())
                    } else {
                        objectOperation.onError(response.errorBody())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}