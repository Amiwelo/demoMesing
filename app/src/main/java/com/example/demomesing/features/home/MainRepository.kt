package com.example.demomesing.features.home

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import com.google.gson.JsonIOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import org.json.JSONException
import java.lang.Exception

class MainRepository: MainDataSource{
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
                        objectOperation.onSuccess(response.body())
                    } else {
                        objectOperation.onError(response.errorBody())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override fun getServices(idServ: Int, objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = "1"
        parameter["idServ"] = idServ.toString()

        callServices = ApiConfig.instanceClient()

        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response = callServices.getAllServices(parameter)
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        objectOperation.onSuccess(response.body())
                    } else {
                        objectOperation.onError(response.errorBody())
                    }
                }
            } catch (e: JSONException){
                e.printStackTrace()
            }
        }
    }
}