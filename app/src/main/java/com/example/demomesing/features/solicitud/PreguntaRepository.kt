package com.example.demomesing.features.solicitud

import android.util.Log
import com.example.demomesing.data.ApiConfig
import com.example.demomesing.data.CallServices
import com.example.demomesing.data.ObjectOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PreguntaRepository : PreguntaDataSource {

    private lateinit var parameter: MutableMap<String, String>
    private lateinit var callServices: CallServices

    override fun createSolicitude(
        p1: Int,
        p2: Int,
        p3: Int,
        cel: Int,
        email: String,
        id: Int,
        idOfer: Int,
        objectOperation: ObjectOperation
    ) {
        parameter = HashMap()
        parameter["Opcion"] = "2"
        parameter["IdTipServ"] = "2"
        parameter["IdEstSol"] = "1"
        parameter["PregUno"] = p1.toString()
        parameter["PregDos"] = p2.toString()
        parameter["PregTres"] = p3.toString()
        parameter["NumeContac"] = cel.toString()
        parameter["CorrContac"] = email
        parameter["IdUsu"] = id.toString()
        parameter["IdUsuEsp"] = idOfer.toString()

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = callServices.createSolicitude(parameter)
                withContext(Dispatchers.Main) {
                    //objectOperation.onSuccess(response)
                    Log.i("RESPONSESOLICITUDE", "${response.cMsj}")
                    Log.i("RESPONSESOLICITUDE", "${response.cMsjDetail}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                objectOperation.onError("ERROR")
            }
        }
    }

    override fun getPreguntas(objectOperation: ObjectOperation) {
        parameter = HashMap()
        parameter["Opcion"] = "1"

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = callServices.getListQuestion(parameter)
                withContext(Dispatchers.Main) {
                    if (response.cMsj == "OK") {
                        objectOperation.onSuccess(response.listPreguntas)
                    } else {
                        objectOperation.onError(response.cMsjDetail)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                objectOperation.onError("ERROR")
            }
        }
    }

}