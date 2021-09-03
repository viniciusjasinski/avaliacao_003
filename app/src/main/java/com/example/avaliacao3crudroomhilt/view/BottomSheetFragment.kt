package com.example.avaliacao3crudroomhilt.view

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.avaliacao3crudroomhilt.view_model.BottomSheetViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.adapter.PatientAdapter
import com.example.avaliacao3crudroomhilt.databinding.BottomSheetFragmentBinding
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment() : BottomSheetDialogFragment() {

    companion object {
        fun newPatientInstance(idPatient: Int): BottomSheetFragment {
            return BottomSheetFragment().apply {
                val args = Bundle()
                args.putInt("patient_id_key", idPatient)
                this.arguments = args
            }
        }

        fun newSpecialtyInstance(idSpecialty: Int): BottomSheetFragment {
            return BottomSheetFragment().apply {
                val args = Bundle()
                args.putInt("specialty_id_key", idSpecialty)
                this.arguments = args
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_fragment, container, false)
    }

    private lateinit var viewModel: BottomSheetViewModel
    private lateinit var binding: BottomSheetFragmentBinding

    private val observePatientInfo = Observer<PatientModel> { patient ->
        if (binding.textInputLayoutPatientAgeEdit.visibility == View.INVISIBLE ||
            binding.textInputLayoutSexEdit.visibility == View.INVISIBLE) {

            binding.textInputLayoutPatientAgeEdit.visibility = View.VISIBLE
            binding.textInputLayoutSexEdit.visibility = View.VISIBLE
        }
        binding.editTextIdEdit.setText(patient.patient_id.toString())
        binding.editTextNameEdit.setText(patient.patient_name)
        binding.editTextPatientAgeEdit.setText(patient.patient_age.toString())
        binding.editTextPatientSexEdit.setText(patient.patient_sex)
    }

    private val observeSpecialtyInfo = Observer<SpecialtyModel> { specialty ->
        if (binding.textInputLayoutPatientAgeEdit.visibility == View.VISIBLE ||
            binding.textInputLayoutSexEdit.visibility == View.VISIBLE) {

            binding.textInputLayoutPatientAgeEdit.visibility = View.INVISIBLE
            binding.textInputLayoutSexEdit.visibility = View.INVISIBLE
        }
        binding.editTextIdEdit.setText(specialty.specialty_id.toString())
        binding.editTextNameEdit.setText(specialty.specialty_name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = BottomSheetFragmentBinding.bind(view)
        val patientId = arguments?.getInt("patient_id_key")
        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)

        viewModel.patient.observe(viewLifecycleOwner, observePatientInfo)

        if (patientId != null && patientId != 0) {
            viewModel.getOnePatient(patientId)
            saveEditPatient(patientId)
        }

        val specialtyId = arguments?.getInt("specialty_id_key")

        viewModel.specialty.observe(viewLifecycleOwner, observeSpecialtyInfo)

        if (specialtyId != null && specialtyId != 0) {
            viewModel.getOneSpecialty(specialtyId)
            saveEditSpecialty(specialtyId)
        }

    }


    private fun saveEditPatient(patientId: Int) {
        binding.buttonSaveEdit.setOnClickListener {
            val name = binding.editTextNameEdit.text.toString()
            val age = binding.editTextPatientAgeEdit.text.toString()
            val sex = binding.editTextPatientSexEdit.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && sex.isNotEmpty()) {
                val patient = PatientModel(patientId, name, age.toInt(), sex)
                viewModel.updatePatient(patient)
                this.dismiss()
            }
        }
    }

    private fun saveEditSpecialty(specialtyId: Int) {
        binding.buttonSaveEdit.setOnClickListener {
            val name = binding.editTextNameEdit.text.toString()

            if (name.isNotEmpty()) {
                viewModel.updateSpecialty(SpecialtyModel(specialtyId, name))
                this.dismiss()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        parentFragmentManager.findFragmentByTag("tag_patient")?.apply {
            (this as? PatientFragment)?.refreshAdapter()
        }
        parentFragmentManager.findFragmentByTag("tag_specialty")?.apply {
            (this as? SpecialtyFragment)?.refreshAdapter()
        }

    }

}