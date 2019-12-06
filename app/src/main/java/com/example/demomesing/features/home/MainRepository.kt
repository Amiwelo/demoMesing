package com.example.demomesing.features.home

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException

class MainRepository : MainDataSource {
    private lateinit var parameter: MutableMap<String, String>
    private lateinit var callServices: CallServices

    override fun launchMain(val1: Int, val2: Int, val3: Int, objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = val1.toString()
        parameter["IdRol"] = val2.toString()
        parameter["IdPer"] = val3.toString()

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.Main).launch {
            val response = callServices.lstMain(parameter)
            try {
                withContext(Dispatchers.Main) {
                    Log.i("MAIN", "$response")
                    objectOperation.onSuccess(response)
                }
            } catch (e: Exception) {
                objectOperation.onError(response.msj)
                e.printStackTrace()
            }
        }
    }

    val gson = Gson()
    override fun getServices(idServ: Int, param: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = "2"
        parameter["IdServ"] = idServ.toString()
        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.Main).launch {
            val response = callServices.getAllServices(parameter)
            try {
                withContext(Dispatchers.Main) {
                    param.onSuccess(response.listObjects)
                }
            } catch (e: Exception) {
                param.onError(response.cMsj)
                e.printStackTrace()
            }
        }
    }

    override fun createUser(
        name: String?,
        lastNamePat: String?,
        lastNameMat: String?,
        email: String?,
        userName: String?,
        pwd: String?,
        objectOperation: ObjectOperation
    ) {
        parameter = HashMap()
        parameter["Opcion"] = "1"
        parameter["PriNomUsu"] = name!!
        parameter["ApePatUsu"] = lastNamePat!!
        parameter["ApeMatUsu"] = lastNameMat!!
        parameter["EmaUsu"] = email!!
        parameter["LogUsu"] = userName!!
        parameter["PwdUsu"] = pwd!!
        parameter["IdEstUsu"] = "4"
        parameter["IdRol"] = "2"
        parameter["IdPer"] = "6"
        parameter["Origen"] = "1"

        callServices = ApiConfig.instanceClient()

        CoroutineScope(Dispatchers.Main).launch {
            val response = callServices.addUser(parameter)
            try {
                withContext(Dispatchers.Main) {
                    if (response.codeStatus != 200) {
                        objectOperation.onError(response)
                    } else {
                        objectOperation.onSuccess(response.cMsj)
                    }
                }
            } catch (e: Exception) {
                objectOperation.onError(response)
                e.printStackTrace()
            }

        }
    }

    override fun getCategorias(objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = "1"
        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.Main).launch {
            val response = callServices.getCategorias(parameter)
            try {
                withContext(Dispatchers.Main) {
                    if (response.msj == "OK") {
                        objectOperation.onSuccess(response.listCategoria)
                    } else {
                        objectOperation.onError(response.msjDetail)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                objectOperation.onError(response.msj)
            }
        }
    }

    override fun getSolicitudesFromUser(id: Int, objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = "4"
        parameter["IdUsuSol"] = id.toString()

        CoroutineScope(Dispatchers.Main).launch {
            val response = callServices.getSolicitude(parameter)
            try {
                withContext(Dispatchers.Main){
                    if(response.cMsj == "OK"){
                        objectOperation.onSuccess(response.listObjects)
                    } else {
                        objectOperation.onError(response.cMsjDetail)
                    }
                }
            } catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
}