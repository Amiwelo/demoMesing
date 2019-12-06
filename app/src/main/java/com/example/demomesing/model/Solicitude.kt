package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Solicitude (
@SerializedName("ID_SOLI")
val idSoli: Int,
@SerializedName("FEC_SOLI")
val fecSoli: String,
@SerializedName("ID_TIP_SERV")
val idTipServi: Int,
@SerializedName("DES_TIP_SERV")
val desTipServi: String,
@SerializedName("ID_EST_SOLI")
val idEstSoli: Int,
@SerializedName("DES_EST_SOLI")
val desEstSoli: String,
@SerializedName("NUME_CONTAC")
val numeContact: String,
@SerializedName("CORR_CONTAC")
val corrContact: String,
@SerializedName("PRI_NOM_USU_ESPECIALISTA")
val nomEspecialista: String,
@SerializedName("APE_PAT_USU_ESPECIALISTA")
val apePatEspecialista: String,
@SerializedName("APE_MAT_USU_ESPECIALISTA")
val apeMatEspecialista: String,
@SerializedName("IMA_USU_ESPECIALISTA")
val imgEspecialista: String
): Serializable