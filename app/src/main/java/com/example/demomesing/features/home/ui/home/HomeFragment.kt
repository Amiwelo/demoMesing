package com.example.demomesing.features.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomesing.R
import com.example.demomesing.base.BaseFragment
import com.example.demomesing.di.Injection
import com.example.demomesing.features.details.DetailActivity
import com.example.demomesing.model.Categoria
import com.example.demomesing.model.Servicios
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: ServiceAdapter


    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = progress_bar
        showProgressBar()
        initApp()

        spinner_categoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(context, "Seleccion $p2 , $p3 ", Toast.LENGTH_LONG).show()
                val categoria = spinner_categoria.selectedItem as Categoria
                homeViewModel.getServices(categoria.idServ)
            }

        }

    }

    private val body = Observer<List<Servicios>> {
        adapter.listServicios = it
        rv_servicios.adapter = adapter
        adapter.setData(it)
    }
    private val bodyCat = Observer<List<Categoria>> {
        spinnerAdapter(it)
    }

    private fun initApp() {
        homeViewModel =
            ViewModelProviders.of(this, HomeViewModelFactory(Injection.getHome()))
                .get(HomeViewModel::class.java)

        rv_servicios.layoutManager = LinearLayoutManager(context)
        adapter = ServiceAdapter(object : Listener {
            override fun onClick(data: Servicios, position: Int) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("dataOfertante", data)
                startActivity(intent)
            }
        })
        homeViewModel.listService.observe(this, body)
        homeViewModel.responseCat.observe(this, bodyCat)
        homeViewModel.realice.observe(this, realice)
        homeViewModel.getCat()
    }

    private val realice = Observer<Boolean> {
        if(it){
            loader()
        } else {
            toast("Error al obtener datos")
        }
    }
    private fun spinnerAdapter(listCategorias: List<Categoria>){
        val arrayAdapter: ArrayAdapter<Categoria> = ArrayAdapter(context!!.applicationContext, android.R.layout.simple_spinner_item, listCategorias)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner_categoria.adapter = arrayAdapter
    }

    private fun loader() {
        Handler().postDelayed({ hideProgressBarr() }, 500)
    }
}