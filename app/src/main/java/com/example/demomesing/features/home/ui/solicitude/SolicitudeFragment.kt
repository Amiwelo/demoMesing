package com.example.demomesing.features.home.ui.solicitude

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomesing.R
import com.example.demomesing.base.BaseFragment
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.di.Injection
import com.example.demomesing.model.ResponseSolicitude
import com.example.demomesing.model.Solicitude
import kotlinx.android.synthetic.main.fragment_gallery.*

class SolicitudeFragment : BaseFragment() {

    private lateinit var solicitudeViewModel: SolicitudeViewModel
    private lateinit var shPreference: ShPreference
    private lateinit var adapter: SolicitudeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initApp()

    }

    override fun getLayout(): Int {
        return R.layout.fragment_gallery
    }
    private fun initApp(){
        shPreference = ShPreference(context!!.getSharedPreferences(ShPreference.PREFERENCE_NAME, Context.MODE_PRIVATE), context!!.applicationContext)
        rv_solicitud.layoutManager = LinearLayoutManager(context)
        solicitudeViewModel =
            ViewModelProviders.of(this, SolicitudeViewModelFactory(Injection.getSolicitude()))
                .get(SolicitudeViewModel::class.java)
        solicitudeViewModel.getSolicitudesFromUser(shPreference.user!!.id)
        solicitudeViewModel.responseBody.observe(this, body)
    }

    private val body = Observer<List<Solicitude>> {
        adapter.listServicios = it
        rv_solicitud.adapter = adapter
        adapter.setData(it)
    }
}