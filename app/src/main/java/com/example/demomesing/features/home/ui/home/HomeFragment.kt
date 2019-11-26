package com.example.demomesing.features.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomesing.R
import com.example.demomesing.di.Injection
import com.example.demomesing.features.details.DetailActivity
import com.example.demomesing.model.Categoria
import com.example.demomesing.model.Servicios
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: ServiceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this, HomeViewModelFactory(Injection.getHome()))
                .get(HomeViewModel::class.java)

        homeViewModel.listServicios.observe(this, body)
        homeViewModel.responseCategorias.observe(this, bodyCateg)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    private val bodyCateg = Observer<List<Categoria>> {
        spinnerAdapter(it)
    }

    private fun initApp() {

        homeViewModel.getCategorias()

        rv_servicios.layoutManager = LinearLayoutManager(context)
        adapter = ServiceAdapter(object : Listener {
            override fun onClick(data: Servicios, position: Int) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("dataOfertante", data)
                startActivity(intent)
            }
        })
    }

    private fun spinnerAdapter(listCategorias: List<Categoria>){
        val arrayAdapter: ArrayAdapter<Categoria> = ArrayAdapter(context!!.applicationContext, android.R.layout.simple_spinner_item, listCategorias)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner_categoria.adapter = arrayAdapter
    }
    private fun message(cad: String) {
        Log.i("TAG", cad)
    }
}