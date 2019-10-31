package com.example.demomesing.features.home

import com.example.demomesing.data.ObjectOperation

interface HomeDataSource {
    fun launchMain(val1: Int, val2: Int, val3: Int, objectOperation: ObjectOperation)
}