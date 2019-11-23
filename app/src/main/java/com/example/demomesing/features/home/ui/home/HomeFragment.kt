package com.example.demomesing.features.home.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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
            ViewModelProviders.of(this, HomeViewModelFactory(Injection.getHome()))
                .get(HomeViewModel::class.java)

        homeViewModel.listServicios.observe(this, body)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initApp()

        simpleSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 == null || p0 == ""){
                    return false
                } else {
                    homeViewModel.getServices(p0!!.toInt())
                }
                return true
            }
        })

    }

    private val body = Observer<List<Servicios>> {
        rv_servicios.layoutManager = LinearLayoutManager(context)
        adapter.listServicios = it
        rv_servicios.adapter = adapter
        adapter.setData(it)
    }

    private fun initApp() {
        adapter = ServiceAdapter(object : Listener {
            override fun onClick(data: Servicios, position: Int) {
                Toast.makeText(context, "HI", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun message(cad: String) {
        Log.i("TAG", cad)
    }
}