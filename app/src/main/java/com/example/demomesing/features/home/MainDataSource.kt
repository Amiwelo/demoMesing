package com.example.demomesing.features.home

import com.example.demomesing.data.ObjectOperation

interface MainDataSource {
    fun launchMain(val1: Int, val2: Int, val3: Int, objectOperation: ObjectOperation)
    fun getServices(idServ: Int, objectOperation: ObjectOperation)
}