package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseCategoria (
@SerializedName("cMsj")
val msj: String,
@SerializedName("cMsjDetail")
val msjDetail: String,
@SerializedName("DtCollection")
val listCategoria: List<Categoria>
):Serializable{
}