package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.ScheduleFragmentBinding
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor
import com.example.avaliacao3crudroomhilt.view_model.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.schedule_fragment) {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var binding: ScheduleFragmentBinding
    private var listOfPatients: List<PatientModel>? = null
    private lateinit var selectedPatient: PatientModel
    private var listOfDoctorsWithSpecialty: List<DoctorWithSpecialty>? = null
    private lateinit var selectedDoctorWithSpecialty: DoctorWithSpecialty

    private val observeSchedules = Observer<List<SchedulePatientDoctor>> {

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

        viewModel.allSchedules.observe(viewLifecycleOwner, observeSchedules)
        viewModel.allPatients.observe(viewLifecycleOwner, observePatients)
        viewModel.allDoctors.observe(viewLifecycleOwner, observeDoctors)

        viewModel.fetchAllSchedules()
        viewModel.fetchAllPatients()
        viewModel.fetchAllDoctors()

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

}