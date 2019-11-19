package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
@SerializedName("ID_US")
var id: Int,
@SerializedName("LOG_USU")
var log_usu: String,
@SerializedName("PRI_NOM_USU")
var nom_usu: String,
@SerializedName("APE_PAT_USU")
var ape_pat: String,
@SerializedName("APE_MAT_USU")
var ape_mat: String,
@SerializedName("EMA_USU")
var email: String,
@SerializedName("IMA_USU")
var img: String,
@SerializedName("ID_EST_USU")
var id_est_usu: Int,
@SerializedName("ID_ROL")
var id_rol: Int,
@SerializedName("ID_PER")
var id_per: Int,
var error: String
): Serializable