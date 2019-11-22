package com.example.demomesing.features.home

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.model.ResponseService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainRepository : MainDataSource {
    private lateinit var parameter: MutableMap<String, String>
    private lateinit var callServices: CallServices

    override fun launchMain(val1: Int, val2: Int, val3: Int, objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = val1.toString()
        parameter["IdRol"] = val2.toString()
        parameter["IdPer"] = val3.toString()

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = callServices.lstMain(parameter)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
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

    val gson = Gson()
    override fun getServices(idServ: Int, param: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = "1"
        parameter["IdServ"] = idServ.toString()
        Log.i("TAG SERVICES", "Servicios todos")
        callServices = ApiConfig.instanceClient()
        val call: Call<ResponseService> = callServices.getAllServices(parameter)

        call.enqueue(object : Callback<ResponseService> {
            override fun onFailure(call: Call<ResponseService>, t: Throwable) {
                    param.onError("error")
            }

            override fun onResponse(
                call: Call<ResponseService>,
                response: Response<ResponseService>
            ) {
                if(response.isSuccessful){
                    val objService = response.body()!!.listObjects
                    param.onSuccess(objService)
                } else {
                    Log.ERROR
                }

            }

        })
    }
    /*

        parameter = HashMap()
        parameter["Opcion"] = "1"
        parameter["IdServ"] = idServ.toString()
        Log.i("TAG SERVICES", "Servicios todos")
        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = callServices.getAllServices(parameter)
                withContext(Dispatchers.Main) {
                    if (response.isExecuted) {

                        val objList = response
                        Log.i("TAG", "${objList}")
                        param.onSuccess(objList)

                    } else {
                        Log.ERROR
                    }
                }
            } catch (e: Exception) {
                param.onError(e.printStackTrace())
            }
        }

    */
}