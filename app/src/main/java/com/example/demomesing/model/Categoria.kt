package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categoria (
@SerializedName("ID_SERV")
val idServ: Int,
@SerializedName("DES_SERV")
val desServ: String,
@SerializedName("ID_ICO")
val idIco: Int,
@SerializedName("DES_ICO")
val desIco: String
): Serializable{
    override fun toString(): String {
        return desServ
    }
}