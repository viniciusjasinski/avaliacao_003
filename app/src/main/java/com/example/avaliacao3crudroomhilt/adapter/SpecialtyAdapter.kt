package com.example.avaliacao3crudroomhilt.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.ItemSpecialtyBinding
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.example.avaliacao3crudroomhilt.utils.SpecialtyClickableItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SpecialtyAdapter (private val clickableItem: SpecialtyClickableItem) : RecyclerView.Adapter<ItemSpecialtyViewHolder>() {

    private val listOfSpecialties: MutableList<SpecialtyModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSpecialtyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specialty, parent, false)
        return ItemSpecialtyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemSpecialtyViewHolder, position: Int) {
        listOfSpecialties[position].apply {
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
        return listOfSpecialties.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newList: List<SpecialtyModel>) {
        listOfSpecialties.clear()
        listOfSpecialties.addAll(newList)
        notifyDataSetChanged()
    }

}

class ItemSpecialtyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemSpecialtyBinding = ItemSpecialtyBinding.bind(itemView)

    fun bind(specialtyModel: SpecialtyModel) {
        binding.textViewSpecialtyId.text = "Id da especialidade: ${specialtyModel.specialty_id.toString()}"
        binding.textViewSpecialtytName.text = "Nome: ${specialtyModel.specialty_name}"
    }

}