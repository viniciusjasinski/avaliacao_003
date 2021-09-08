package com.example.avaliacao3crudroomhilt.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.ItemDoctorBinding
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.utils.DoctorClickableItem
import com.example.avaliacao3crudroomhilt.utils.SpecialtyClickableItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DoctorAdapter(val clickableItem: DoctorClickableItem): RecyclerView.Adapter<ItemDoctorViewHolder>() {

    private var listOfDoctors: MutableList<DoctorWithSpecialty> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return ItemDoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemDoctorViewHolder, position: Int) {
        listOfDoctors[position].apply {
            holder.bind(this)
            holder.itemView.findViewById<FloatingActionButton>(R.id.trash_action_button).setOnClickListener {
                clickableItem.clickTrashIcon(this)
            }
            holder.itemView.findViewById<FloatingActionButton>(R.id.edit_action_button).setOnClickListener {
                clickableItem.clickEditIcon(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfDoctors.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newList: List<DoctorWithSpecialty>) {
        listOfDoctors.clear()
        listOfDoctors.addAll(newList)
        notifyDataSetChanged()
    }
}


class ItemDoctorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var binding: ItemDoctorBinding = ItemDoctorBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(doctorWithSpecialty: DoctorWithSpecialty) {
        binding.textViewDoctorId.text = "Id do médico(a): ${doctorWithSpecialty.doctor!!.doctor_id}"
        binding.textViewDoctorName.text = "Nome: ${doctorWithSpecialty.doctor.doctor_name}"
        binding.textViewDoctorSpecialty.text = "Especialidade: ${doctorWithSpecialty.specialty?.specialty_name}"
    }

}