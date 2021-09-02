package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.adapter.PatientAdapter
import com.example.avaliacao3crudroomhilt.databinding.PatientFragmentBinding
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.view_model.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientFragment : Fragment(R.layout.patient_fragment) {

    companion object {
        fun newInstance() = PatientFragment()
    }

    private lateinit var viewModel: PatientViewModel
    private lateinit var binding: PatientFragmentBinding
    private var adapter = PatientAdapter()

    private val observePacientsList = Observer<List<PatientModel>> { listOfPatients ->
        adapter.refresh(listOfPatients)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PatientFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)

        binding.recyclerViewPatient.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPatient.adapter = adapter

        viewModel.patientList.observe(viewLifecycleOwner, observePacientsList)

        getScreenEvents()

        viewModel.fetchAllPatientsList()



    }

    fun getScreenEvents() {
        binding.buttonInsertPacient.setOnClickListener {
            val name = binding.editTextPatientName.text.toString()
            val age = binding.editTextPatientAge.text.toString()
            val sex = binding.editTextPatientSex.text.toString()

            if(name.isNotEmpty() && age.isNotEmpty() && sex.isNotEmpty()) {
                val patient = PatientModel(patient_name = name, patient_age = age.toInt(), patient_sex = sex)
                viewModel.insertPatient(patient)
            }
        }
        binding.buttonShowPacientList.setOnClickListener {
            if(binding.recyclerViewPatient.visibility == View.VISIBLE) {
                binding.recyclerViewPatient.visibility = View.INVISIBLE
            } else {
                binding.recyclerViewPatient.visibility = View.VISIBLE
            }
        }
    }


}