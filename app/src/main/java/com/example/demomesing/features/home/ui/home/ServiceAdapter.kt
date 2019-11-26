package com.example.demomesing.features.home.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demomesing.R
import com.example.demomesing.model.Servicios
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_services.view.*

class ServiceAdapter(private val listener: Listener) :RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    lateinit var listServicios: List<Servicios>



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: Servicios, listener: Listener)= with(itemView){
            tv_cellphone_item.text = data.cel_usu
            tv_name_item.text = data.pri_nom_usu+ " " + data.ape_pat_usu
            tv_service_item.text = data.seu_usu
            //tv_enterprise_item.text = data.des_tip_serv
            Picasso.get().load(data.ima_usu).placeholder(R.color.blue_bg_light).into(img_photo_item)
            item_ofertante.setOnClickListener { listener.onClick(data, adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listServicios.size
    }

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {
        holder.bind(listServicios[position], listener)
    }
    fun setData(lista :List<Servicios>){
        this.listServicios = lista
        notifyDataSetChanged()
    }
}
interface Listener{
    fun onClick(data: Servicios, position: Int)
}