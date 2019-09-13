package com.example.demomesing.model

import java.io.Serializable

data class Usuario constructor(
    val nombre: String, val user: String, val pwd: String
):Serializable
