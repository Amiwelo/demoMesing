package com.example.demomesing.features.solicitud

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
        objectOperation: ObjectOperation
    ) {
        parameter = HashMap()
        parameter["Opcion"] = "2"
        parameter["IdTipServ"] = ""
        parameter["IdEstSol"] = ""
        parameter["PregUno"] = ""
        parameter["PregDos"] = ""
        parameter["PregTres"] = ""
        parameter["NumeContac"] = ""
        parameter["CorrContac"] = ""
        parameter["IdUsu"] = ""

        callServices = ApiConfig.instanceClient()
        CoroutineScope(Dispatchers.Main).launch {

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