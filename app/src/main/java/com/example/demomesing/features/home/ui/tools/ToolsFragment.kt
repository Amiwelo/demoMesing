package com.example.demomesing.features.home.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.di.Injection
import com.example.demomesing.features.home.ui.home.HomeViewModelFactory
import com.example.demomesing.model.ResponseUsuario
import kotlinx.android.synthetic.main.fragment_tools.*

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this, ToolsViewModelFactory(Injection.getHome())).get(ToolsViewModel::class.java)



        return inflater.inflate(R.layout.fragment_tools, container, false)
    }
    private val message= Observer<ResponseUsuario> {
        Toast.makeText(context, it.cMsj+" "+it.cMsjDetail , Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initApp()
        btn_registrar.setOnClickListener {
            toolsViewModel.createUser(
                et_nombre_usu_registro.text.toString(),
                et_ape_pat_usu_registro.text.toString(),
                et_ape_mat_usu_registro.text.toString(),
                et_email_usu_registro.text.toString(),
                et_usurio_registro.text.toString(),
                et_pwd_registro.text.toString()
            )
            //cleanFields()
        }
    }
    private fun cleanFields(){
        et_nombre_usu_registro.setText("")
        et_ape_pat_usu_registro.setText("")
        et_ape_mat_usu_registro.setText("")
        et_email_usu_registro.setText("")
        et_usurio_registro.setText("")
        et_pwd_registro.setText("")
    }
    private fun initApp(){
        toolsViewModel.msj.observe(this, message)
    }
    /*fun x(){
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        toolsViewModel.text.observe(this, Observer {
            textView.text = it
        })
    }*/
}