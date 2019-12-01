package com.example.demomesing.base

interface IBaseActivity {
    interface View {
        fun toast(msj: String)
        fun showError(msjError: String)
        fun hideProgressBarr()
        fun showProgressBar()
    }
}