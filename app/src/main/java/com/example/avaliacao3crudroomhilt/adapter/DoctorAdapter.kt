package com.example.avaliacao3crudroomhilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.model.DoctorModel

class DoctorAdapter: RecyclerView.Adapter<ItemDoctorViewHolder>() {

    private var listOfDoctors: MutableList<DoctorModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return ItemDoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemDoctorViewHolder, position: Int) {
        listOfDoctors[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return listOfDoctors.size
    }
}


class ItemDoctorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(doctorModel: DoctorModel) {

    }

}