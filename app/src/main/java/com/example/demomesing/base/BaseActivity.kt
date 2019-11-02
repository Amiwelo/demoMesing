package com.example.demomesing.base

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity(), Base.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(getLayout())
    }

    @LayoutRes
    abstract fun getLayout(): Int

    override fun toast(msj: String) {
        Toast.makeText(applicationContext, msj, Toast.LENGTH_SHORT).show()
    }

    override fun showError(msjError: String){
        Toast.makeText(applicationContext, msjError, Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBarr(){
        progressBarLogin.visibility = View.GONE
    }

    override fun showProgressBar(){
        progressBarLogin.visibility = View.VISIBLE
    }


}


