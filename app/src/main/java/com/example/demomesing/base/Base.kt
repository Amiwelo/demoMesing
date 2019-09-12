package com.example.demomesing.base

interface Base {
    interface View {
        fun toast(msj: String)
        fun showError(msjError: String)
        fun hideProgressBarr()
        fun showProgressBarr()
    }
}