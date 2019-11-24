package com.example.demomesing.features.home

import com.example.demomesing.data.ObjectOperation

interface MainDataSource {
    fun launchMain(val1: Int, val2: Int, val3: Int, objectOperation: ObjectOperation)
    fun getServices(idServ: Int, objectOperation: ObjectOperation)
    fun createUser(
        name: String?,
        lastNamePat: String?,
        lastNameMat: String?,
        email: String?,
        userName: String?,
        pwd: String?,
        objectOperation: ObjectOperation
    )
}