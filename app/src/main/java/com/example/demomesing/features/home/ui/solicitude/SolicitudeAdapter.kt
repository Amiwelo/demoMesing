package com.example.demomesing.features.home.ui.solicitude

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demomesing.R
import com.example.demomesing.model.Servicios
import com.example.demomesing.model.Solicitude
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_services.view.*
import kotlinx.android.synthetic.main.item_solicitude.view.*

class SolicitudeAdapter(private val listener: Listener) :RecyclerView.Adapter<SolicitudeAdapter.ViewHolder>() {
    lateinit var listServicios: List<Solicitude>



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: Solicitude, listener: Listener)= with(itemView){
            tv_nombre_especialista.text = data.nomEspecialista +" "+data.apePatEspecialista
            tv_nr_solicitud.text = data.idSoli.toString()
            tv_fec_solicitud.text = data.fecSoli
            tv_des_tip_servicio.text = data.desTipServi
            tv_correo_contact.text = data.corrContact
            tv_numero_contact.text = data.numeContact
            Picasso.get().load(data.imgEspecialista).placeholder(R.color.blue_bg_light).into(img_profile)

            item_solicitude.setOnClickListener { listener.onClick(data, adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listServicios.size
    }

    override fun onBindViewHolder(holder: SolicitudeAdapter.ViewHolder, position: Int) {
        holder.bind(listServicios[position], listener)
    }
    fun setData(lista :List<Solicitude>){
        this.listServicios = lista
        notifyDataSetChanged()
    }
}
interface Listener{
    fun onClick(data: Solicitude, position: Int)
}