package com.example.demomesing.features.home.ui.solicitude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.base.BaseFragment
import com.example.demomesing.di.Injection

class SolicitudeFragment : BaseFragment() {

    private lateinit var solicitudeViewModel: SolicitudeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        solicitudeViewModel =
            ViewModelProviders.of(this, SolicitudeViewModelFactory(Injection.getSolicitude()))
                .get(SolicitudeViewModel::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_gallery
    }
    private fun initApp(){

    }
}