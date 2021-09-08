package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avaliacao3crudroomhilt.view_model.DoctorViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.adapter.DoctorAdapter
import com.example.avaliacao3crudroomhilt.databinding.DoctorFragmentBinding
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.example.avaliacao3crudroomhilt.utils.DoctorClickableItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DoctorFragment : Fragment(R.layout.doctor_fragment), DoctorClickableItem {

    companion object {
        fun newInstance() = DoctorFragment()
    }

    private lateinit var viewModel: DoctorViewModel
    private lateinit var binding: DoctorFragmentBinding
    private var adapter = DoctorAdapter(this)
    private var specialtyAutoComplete: SpecialtyModel? = null

    private val observeDoctorsList = Observer<List<DoctorWithSpecialty>> { doctorWithSpecialtyList ->
        adapter.refresh(doctorWithSpecialtyList)
    }
    private val observeSpecialtyList = Observer<List<SpecialtyModel>> {
        chargeAutoComplete(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DoctorFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        binding.recyclerViewDoctor.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDoctor.adapter = adapter

        viewModel.doctorList.observe(viewLifecycleOwner, observeDoctorsList)
        viewModel.specialtyList.observe(viewLifecycleOwner, observeSpecialtyList)
        getScreenEvents()
        viewModel.fetchSpecialty()
        viewModel.fetchDoctors()


    }

    private fun getScreenEvents() {
        binding.buttonInsertDoctor.setOnClickListener {
            val name = binding.editTextDoctorName.text.toString()

            if(name.isNotEmpty() && specialtyAutoComplete != null) {
                println(specialtyAutoComplete!!.specialty_id)
                println(specialtyAutoComplete!!.specialty_id)
                val doctor = DoctorModel(doctor_name = name, doctor_specialtyFK = specialtyAutoComplete!!.specialty_id)
                viewModel.insertDoctor(doctor)
            }
        }
        binding.buttonShowDoctorList.setOnClickListener {
            if(binding.recyclerViewDoctor.visibility == View.VISIBLE) {
                binding.recyclerViewDoctor.visibility = View.INVISIBLE
            } else {
                binding.recyclerViewDoctor.visibility = View.VISIBLE
            }
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

    override fun clickTrashIcon(doctorWithSpecialty: DoctorWithSpecialty) {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(context.getString(R.string.dialog_delete_doctor))
            if(doctorWithSpecialty.doctor != null && doctorWithSpecialty.specialty != null) {
                setMessage(
                    "Você deseja deletar o médico ${doctorWithSpecialty.doctor.doctor_name} " +
                            "que possui a especialidade ${doctorWithSpecialty.specialty.specialty_name}?"
                )
            }
            setPositiveButton(context.getString(R.string.dialog_delete)) { dialog, which ->
                viewModel.deleteDoctor(doctorWithSpecialty.doctor!!)
                dialog.dismiss()
            }
            setNeutralButton(context.getString(R.string.dialog_cancel)) { dialog, which ->
                dialog.cancel()
            }
        }.show()
    }

    override fun clickEditIcon(doctorWithSpecialty: DoctorWithSpecialty) {
        if(doctorWithSpecialty.doctor != null) {
            val bottomSheet =
                BottomSheetFragment.newDoctorInstance(doctorWithSpecialty.doctor.doctor_id)
            bottomSheet.show(parentFragmentManager, "edit_details_doctor")
        }
    }

    fun refreshAdapter() {
        viewModel.fetchDoctors()
    }

}