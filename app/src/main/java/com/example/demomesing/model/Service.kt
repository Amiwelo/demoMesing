package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Service (
@SerializedName("ID_SERV")
val id_serv: Int,
@SerializedName("ID_USU")
val id_usu: Int,
@SerializedName("PRI_NOM_USU")
val pri_nom_usu: String,
@SerializedName("APE_PAT_USU")
val ape_pat_usu: String,
@SerializedName("APE_MAT_USU")
val ape_mat_usu: String,
@SerializedName("IMA_USU")
val ima_usu: String,
@SerializedName("CEL_USU")
val cel_usu: String,
@SerializedName("SEU_USU")
val seu_usu: String,
@SerializedName("DES_TIP_SERV")
val des_tip_serv: String
): Serializable
