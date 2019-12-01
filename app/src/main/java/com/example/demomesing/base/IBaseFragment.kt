package com.example.demomesing.base

interface IBaseFragment {
    interface View {
        fun toast(msj: String)
        fun showError(msjError: String)
        fun hideProgressBarr()
        fun showProgressBar()
    }
}