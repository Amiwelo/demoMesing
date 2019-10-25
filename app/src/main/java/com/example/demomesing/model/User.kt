package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("Status")
    var status: String,
    @SerializedName("CodeStatus")
    var codeStatus: Int,
    @SerializedName("Message")
    var message: String,
    @SerializedName("Token")
    var token: String,
    @SerializedName("TokenExpire")
    var tokenExpire: Int,
    @SerializedName("Id")
    var id: String,
    @SerializedName("Nombre")
    var nameUser: String,
    @SerializedName("Apellido")
    var lastNameUser: String,
    @SerializedName("Email")
    var email: String,
    @SerializedName("Avatar")
    var avatar: String,
    @SerializedName("IdEstadoUsuario")
    var idStatusUser: Int,
    @SerializedName("IdRol")
    var idRol: Int,
    @SerializedName("IdPerfil")
    var idProfile: Int
): Serializable