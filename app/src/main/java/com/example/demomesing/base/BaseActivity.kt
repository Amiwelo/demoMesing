package com.example.demomesing.base

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), IBaseActivity.View {

    lateinit var progressBar: View
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
        progressBar.visibility = View.GONE
    }

    override fun showProgressBar(){
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        progressBar.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}


