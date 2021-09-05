package com.example.avaliacao3crudroomhilt.view

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.avaliacao3crudroomhilt.view_model.BottomSheetViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.BottomSheetFragmentBinding
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
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

        fun newDoctorInstance(idDoctor: Int): BottomSheetFragment {
            return BottomSheetFragment().apply {
                val args = Bundle()
                args.putInt("doctor_id_key", idDoctor)
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
    private var specialtyAutoComplete: SpecialtyModel? = null

    private val observePatientInfo = Observer<PatientModel> { patient ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.VISIBLE
        binding.textInputLayoutSexEdit.visibility = View.VISIBLE
        binding.layoutInput.visibility = View.INVISIBLE
        binding.editTextIdEdit.setText(patient.patient_id.toString())
        binding.editTextNameEdit.setText(patient.patient_name)
        binding.editTextPatientAgeEdit.setText(patient.patient_age.toString())
        binding.editTextPatientSexEdit.setText(patient.patient_sex)
    }

    private val observeSpecialtyInfo = Observer<SpecialtyModel> { specialty ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.INVISIBLE
        binding.textInputLayoutSexEdit.visibility = View.INVISIBLE
        binding.layoutInput.visibility = View.INVISIBLE
        binding.editTextIdEdit.setText(specialty.specialty_id.toString())
        binding.editTextNameEdit.setText(specialty.specialty_name)
    }

    private val observeSpecialtyListInfo = Observer<List<SpecialtyModel>> { specialtyList ->
        chargeAutoComplete(specialtyList)
    }

    private val observeDoctorInfo = Observer<DoctorWithSpecialty> { doctorWithSpecialty ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.INVISIBLE
        binding.textInputLayoutSexEdit.visibility = View.INVISIBLE
        binding.layoutInput.visibility = View.VISIBLE
        binding.editTextIdEdit.setText(doctorWithSpecialty.doctor!!.doctor_id.toString())
        binding.editTextNameEdit.setText(doctorWithSpecialty.doctor.doctor_name)
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


        val doctorId = arguments?.getInt("doctor_id_key")

        viewModel.doctorWithSpecialty.observe(viewLifecycleOwner, observeDoctorInfo)
        viewModel.specialtyList.observe(viewLifecycleOwner, observeSpecialtyListInfo)

        if (doctorId != null && doctorId != 0) {
            viewModel.getOneDoctorWithSpecialty(doctorId)
            viewModel.getAllSpecialty()
            saveEditDoctor(doctorId)
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
                viewModel.getAllSpecialty()
                this.dismiss()
            }
        }
    }

    private fun saveEditDoctor(doctorId: Int) {
        binding.buttonSaveEdit.setOnClickListener {
            val name = binding.editTextNameEdit.text.toString()

            if (name.isNotEmpty() && specialtyAutoComplete != null) {
                viewModel.updateDoctor(DoctorModel(doctorId, name, specialtyAutoComplete!!.specialty_id))
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
        parentFragmentManager.findFragmentByTag("tag_doctor")?.apply {
            (this as? DoctorFragment)?.refreshAdapter()
        }

    }

    private fun chargeAutoComplete(specialtyModel: List<SpecialtyModel>) {
        val arr = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            specialtyModel
        )
        binding.autoCompleteSpecialty.setAdapter(arr)
        binding.autoCompleteSpecialty.setOnItemClickListener { adapterView, view, i, l ->
            specialtyAutoComplete = adapterView.getItemAtPosition(i) as SpecialtyModel
        }

    }

}