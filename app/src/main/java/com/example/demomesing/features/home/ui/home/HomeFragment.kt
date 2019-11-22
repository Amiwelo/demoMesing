package com.example.demomesing.features.home.ui.home

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomesing.R
import com.example.demomesing.di.Injection
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
            ViewModelProviders.of(this, HomeViewModelFactory(Injection.getHome())).get(HomeViewModel::class.java)

        homeViewModel.getServices(1)
        homeViewModel.listServicios.observe(this, body)

        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initApp()
    }
    private val body = Observer<List<Servicios>> {
        rv_servicios.layoutManager = LinearLayoutManager(context)
        adapter.listServicios = it
        rv_servicios.adapter = adapter
    }
    private fun initApp(){
        adapter = ServiceAdapter(object : Listener {
            override fun onClick(data: Servicios, position: Int) {
                Toast.makeText(context, "HI", Toast.LENGTH_SHORT).show()
            }
        })
    }
}