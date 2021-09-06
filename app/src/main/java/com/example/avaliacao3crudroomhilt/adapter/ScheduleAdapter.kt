package com.example.avaliacao3crudroomhilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.ItemScheduleBinding
import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor

class ScheduleAdapter : RecyclerView.Adapter<ItemScheduleViewHolder>() {

    private var listOfSchedules : MutableList<SchedulePatientDoctor> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ItemScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemScheduleViewHolder, position: Int) {
        listOfSchedules[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return listOfSchedules.size
    }

    fun refresh(newList: List<SchedulePatientDoctor>) {
        listOfSchedules.clear()
        listOfSchedules.addAll(newList)
        notifyDataSetChanged()
    }

}

class ItemScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemScheduleBinding = ItemScheduleBinding.bind(itemView)

    fun bind(schedulePatientDoctor: SchedulePatientDoctor) {
        binding.textViewScheduleId.text = schedulePatientDoctor.scheduleModel!!.schedule_id.toString()
        binding.textViewDoctorSchedule.text = "Paciente: ${schedulePatientDoctor.doctorModel!!.doctor_name}"
        binding.textViewPatientSchedule.text = "Médico: ${schedulePatientDoctor.patientModel!!.patient_name}"
    }

}