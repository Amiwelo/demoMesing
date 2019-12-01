package com.example.demomesing.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(), IBaseFragment.View {
    lateinit var progressBar: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container,false)
    }
    @LayoutRes
    abstract fun getLayout(): Int

    override fun toast(msj: String) {
        Toast.makeText(context!!.applicationContext, msj, Toast.LENGTH_SHORT).show()
    }

    override fun showError(msjError: String){
        Toast.makeText(context!!.applicationContext, msjError, Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBarr(){
        progressBar.visibility = View.GONE
    }

    override fun showProgressBar(){
        //context!!.applicationContext.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        progressBar.visibility = View.VISIBLE
    }

}