package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.adapter.ScheduleAdapter
import com.example.avaliacao3crudroomhilt.databinding.ScheduleFragmentBinding
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.ScheduleModel
import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor
import com.example.avaliacao3crudroomhilt.utils.ScheduleClickableItem
import com.example.avaliacao3crudroomhilt.view_model.ScheduleViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.schedule_fragment), ScheduleClickableItem {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var binding: ScheduleFragmentBinding
    private var listOfPatients: List<PatientModel>? = null
    private var selectedPatient: PatientModel? = null
    private var listOfDoctorsWithSpecialty: List<DoctorWithSpecialty>? = null
    private var selectedDoctorWithSpecialty: DoctorWithSpecialty? = null
    private var adapter = ScheduleAdapter(this)

    private val observeSchedules = Observer<List<SchedulePatientDoctor>> { scheduleList ->
        adapter.refresh(scheduleList)
    }

    private val observePatients = Observer<List<PatientModel>> { patients ->
        listOfPatients = patients
        chargeAutoComplete(listOfPatients!!)
    }


    private val observeDoctors = Observer<List<DoctorWithSpecialty>> { doctorsWithSpecialty ->
        listOfDoctorsWithSpecialty = doctorsWithSpecialty
        chargeAutoComplete(listOfDoctorsWithSpecialty!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ScheduleFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)

        binding.recyclerViewSchedule.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSchedule.adapter = adapter

        viewModel.allSchedules.observe(viewLifecycleOwner, observeSchedules)
        viewModel.allPatients.observe(viewLifecycleOwner, observePatients)
        viewModel.allDoctors.observe(viewLifecycleOwner, observeDoctors)

        getScreenEvents()

        viewModel.fetchAllSchedules()
        viewModel.fetchAllPatients()
        viewModel.fetchAllDoctors()

    }

    private fun getScreenEvents() {
        binding.buttonInsertSchedule.setOnClickListener {
            if(selectedPatient != null && selectedDoctorWithSpecialty != null) {
                viewModel.insertSchedule(ScheduleModel(doctorIdFK = selectedDoctorWithSpecialty!!.doctor!!.doctor_id, patientIdFK = selectedPatient!!.patient_id))
            }
        }
        binding.buttonShowScheduleList.setOnClickListener {
            if (binding.recyclerViewSchedule.visibility == View.VISIBLE) {
                binding.recyclerViewSchedule.visibility = View.INVISIBLE
            } else {
                binding.recyclerViewSchedule.visibility = View.VISIBLE
            }
        }
        binding.editTextFilterSpecialty.apply {
            text.toString()
        }
        binding.editTextFilterSex.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if(it.length > 1) {
                        viewModel.fetchfilterBySex(it.toString())
                    } else {
                        refreshAdapter()
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.editTextFilterSpecialty.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if(it.length > 1) {
                        viewModel.fetchfilterBySpecialty(it.toString())
                    } else {
                        refreshAdapter()
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun <T> chargeAutoComplete(listOfAny: List<T>) {
        val arr = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            listOfAny
        )

        if(listOfAny == listOfPatients) {
            binding.autoCompletePatient.setAdapter(arr)
            binding.autoCompletePatient.setOnItemClickListener { adapterView, view, i, l ->
                selectedPatient = adapterView.getItemAtPosition(i) as PatientModel
            }
        }
        if(listOfAny == listOfDoctorsWithSpecialty) {
            binding.autoCompleteDoctor.setAdapter(arr)
            binding.autoCompleteDoctor.setOnItemClickListener { adapterView, view, i, l ->
                selectedDoctorWithSpecialty = adapterView.getItemAtPosition(i) as DoctorWithSpecialty
            }
        }


    }

    override fun clickTrashIcon(schedulePatientDoctor: SchedulePatientDoctor) {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(context.getString(R.string.dialog_delete_schedule))
            setMessage("Você deseja deletar o agendamendo do médico ${schedulePatientDoctor.doctorModel!!.doctor_name} " +
                    "com o paciente ${schedulePatientDoctor.patientModel!!.patient_name}?")
            setPositiveButton(context.getString(R.string.dialog_delete)) { dialog, which ->
                viewModel.deleteSchedule(schedulePatientDoctor.scheduleModel!!)
                dialog.dismiss()
            }
            setNeutralButton(context.getString(R.string.dialog_cancel)) { dialog, which ->
                dialog.cancel()
            }
        }.show()
    }

    override fun clickEditIcon(schedulePatientDoctor: SchedulePatientDoctor) {
        val bottomSheet = BottomSheetFragment.newScheduleInstance(schedulePatientDoctor.scheduleModel!!.schedule_id)
        bottomSheet.show(parentFragmentManager, "edit_details_schedule")
    }

    fun refreshAdapter() {
        viewModel.fetchAllSchedules()
    }

}