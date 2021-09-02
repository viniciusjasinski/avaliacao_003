package com.example.avaliacao3crudroomhilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.model.PatientModel

class PatientAdapter: RecyclerView.Adapter<ItemPatientViewHolder>() {

    private val listOfPatients : MutableList<PatientModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)
        return ItemPatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemPatientViewHolder, position: Int) {
        listOfPatients[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return listOfPatients.size
    }
}

class ItemPatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(patientModel: PatientModel) {

    }

}