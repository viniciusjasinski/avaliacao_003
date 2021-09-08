package com.example.avaliacao3crudroomhilt.view

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.avaliacao3crudroomhilt.view_model.BottomSheetViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.adapter.ScheduleAdapter
import com.example.avaliacao3crudroomhilt.databinding.BottomSheetFragmentBinding
import com.example.avaliacao3crudroomhilt.model.*
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

        fun newScheduleInstance(idSchedule: Int): BottomSheetFragment {
            return BottomSheetFragment().apply {
                val args = Bundle()
                args.putInt("schedule_id_key", idSchedule)
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
    private var selectedSpecialty: SpecialtyModel? = null
    private var listOfSpecialties: List<SpecialtyModel>? = null
    private var listOfPatients: List<PatientModel>? = null
    private var selectedPatient: PatientModel? = null
    private var listOfDoctorsWithSpecialty: List<DoctorWithSpecialty>? = null
    private var selectedDoctorWithSpecialty: DoctorWithSpecialty? = null

    private val observePatientInfo = Observer<PatientModel> { patient ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.VISIBLE
        binding.textInputLayoutNameEdit.visibility = View.VISIBLE
        binding.textInputLayoutSexEdit.visibility = View.VISIBLE
        binding.layoutInputSpecialty.visibility = View.INVISIBLE
        binding.layoutInputPatient.visibility = View.INVISIBLE
        binding.layoutInputDoctor.visibility = View.INVISIBLE
        binding.editTextIdEdit.setText(patient.patient_id.toString())
        binding.editTextNameEdit.setText(patient.patient_name)
        binding.editTextPatientAgeEdit.setText(patient.patient_age.toString())
        binding.editTextPatientSexEdit.setText(patient.patient_sex)
    }

    private val observeSpecialtyInfo = Observer<SpecialtyModel> { specialty ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.INVISIBLE
        binding.textInputLayoutNameEdit.visibility = View.VISIBLE
        binding.textInputLayoutSexEdit.visibility = View.INVISIBLE
        binding.layoutInputSpecialty.visibility = View.INVISIBLE
        binding.layoutInputPatient.visibility = View.INVISIBLE
        binding.layoutInputDoctor.visibility = View.INVISIBLE
        binding.editTextIdEdit.setText(specialty.specialty_id.toString())
        binding.editTextNameEdit.setText(specialty.specialty_name)
    }

    private val observeDoctorInfo = Observer<DoctorWithSpecialty> { doctorWithSpecialty ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.INVISIBLE
        binding.textInputLayoutSexEdit.visibility = View.INVISIBLE
        binding.textInputLayoutNameEdit.visibility = View.VISIBLE
        binding.layoutInputSpecialty.visibility = View.VISIBLE
        binding.layoutInputPatient.visibility = View.INVISIBLE
        binding.layoutInputDoctor.visibility = View.INVISIBLE
        binding.editTextIdEdit.setText(doctorWithSpecialty.doctor!!.doctor_id.toString())
        binding.editTextNameEdit.setText(doctorWithSpecialty.doctor.doctor_name)
    }

    private val observeScheduleInfo = Observer<SchedulePatientDoctor> { schedule ->

        binding.textInputLayoutPatientAgeEdit.visibility = View.INVISIBLE
        binding.textInputLayoutNameEdit.visibility = View.INVISIBLE
        binding.textInputLayoutSexEdit.visibility = View.INVISIBLE
        binding.layoutInputSpecialty.visibility = View.INVISIBLE
        binding.layoutInputPatient.visibility = View.VISIBLE
        binding.layoutInputDoctor.visibility = View.VISIBLE
        binding.editTextIdEdit.setText(schedule.scheduleModel!!.schedule_id.toString())

    }

    private val observeSpecialtyListInfo = Observer<List<SpecialtyModel>> { specialtiesList ->
        listOfSpecialties = specialtiesList
        chargeAutoComplete(listOfSpecialties!!)
    }

    private val observeDoctorsListInfo = Observer<List<DoctorWithSpecialty>> { doctorsList ->
        listOfDoctorsWithSpecialty = doctorsList
        chargeAutoComplete(listOfDoctorsWithSpecialty!!)
    }

    private val observePatientsListInfo = Observer<List<PatientModel>> { patientsList ->
        listOfPatients = patientsList
        chargeAutoComplete(listOfPatients!!)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BottomSheetFragmentBinding.bind(view)

        val patientId = arguments?.getInt("patient_id_key")
        val specialtyId = arguments?.getInt("specialty_id_key")
        val doctorId = arguments?.getInt("doctor_id_key")
        val scheduleId = arguments?.getInt("schedule_id_key")

        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)

        viewModel.patient.observe(viewLifecycleOwner, observePatientInfo)
        viewModel.specialty.observe(viewLifecycleOwner, observeSpecialtyInfo)
        viewModel.doctorWithSpecialty.observe(viewLifecycleOwner, observeDoctorInfo)
        viewModel.specialtyList.observe(viewLifecycleOwner, observeSpecialtyListInfo)
        viewModel.schedule.observe(viewLifecycleOwner, observeScheduleInfo)
        viewModel.doctorsList.observe(viewLifecycleOwner, observeDoctorsListInfo)
        viewModel.patientsList.observe(viewLifecycleOwner, observePatientsListInfo)

        if (patientId != null && patientId != 0) {
            viewModel.getOnePatient(patientId)
            saveEditPatient(patientId)
        }


        if (specialtyId != null && specialtyId != 0) {
            viewModel.getOneSpecialty(specialtyId)
            saveEditSpecialty(specialtyId)
        }


        if (doctorId != null && doctorId != 0) {
            viewModel.getOneDoctorWithSpecialty(doctorId)
            viewModel.getAllSpecialty()
            saveEditDoctor(doctorId)
        }

        if (scheduleId != null && scheduleId != 0) {
            viewModel.getOneSchedule(scheduleId)
            viewModel.getAllDoctors()
            viewModel.getAllPatients()
            saveEditSchedule(scheduleId)
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

            if (name.isNotEmpty() && selectedSpecialty != null) {
                viewModel.updateDoctor(DoctorModel(doctorId, name, selectedSpecialty!!.specialty_id))
                this.dismiss()
            }
        }
    }

    private fun saveEditSchedule(scheduleId: Int) {
        binding.buttonSaveEdit.setOnClickListener {

            if (selectedPatient != null && selectedDoctorWithSpecialty != null) {
                viewModel.updateSchedule(ScheduleModel(scheduleId, selectedDoctorWithSpecialty!!.doctor!!.doctor_id, selectedPatient!!.patient_id))
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
        parentFragmentManager.findFragmentByTag("tag_schedule")?.apply {
            (this as? ScheduleFragment)?.refreshAdapter()
        }

    }

    private fun <T> chargeAutoComplete(listOfAny: List<T>) {
        val arr = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            listOfAny
        )

        if(listOfAny == listOfSpecialties) {
            binding.autoCompleteSpecialty.setAdapter(arr)
            binding.autoCompleteSpecialty.setOnItemClickListener { adapterView, view, i, l ->
                selectedSpecialty = adapterView.getItemAtPosition(i) as SpecialtyModel
            }
        }

        if (listOfAny == listOfPatients) {
            binding.autoCompletePatient.setAdapter(arr)
            binding.autoCompletePatient.setOnItemClickListener { adapterView, view, i, l ->
                selectedPatient = adapterView.getItemAtPosition(i) as PatientModel
            }
        }
        if (listOfAny == listOfDoctorsWithSpecialty) {
            binding.autoCompleteDoctor.setAdapter(arr)
            binding.autoCompleteDoctor.setOnItemClickListener { adapterView, view, i, l ->
                selectedDoctorWithSpecialty =
                    adapterView.getItemAtPosition(i) as DoctorWithSpecialty
            }
        }
    }

}