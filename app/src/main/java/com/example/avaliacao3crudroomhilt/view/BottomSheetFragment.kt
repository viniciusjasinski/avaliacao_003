package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.avaliacao3crudroomhilt.view_model.BottomSheetViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.BottomSheetFragmentBinding
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(patientId: Int) : BottomSheetFragment{
            return BottomSheetFragment().apply {
                val args = Bundle()
                args.putInt("patient_id_key", patientId)
                this.arguments = args
            }
        }
    }

    private lateinit var viewModel: BottomSheetViewModel
    private lateinit var binding: BottomSheetFragmentBinding

    private val observePatientInfo = Observer<PatientModel> { patient ->
        binding.editTextPatientIdEdit.setText(patient.patient_id.toString())
        binding.editTextPatientNameEdit.setText(patient.patient_name)
        binding.editTextPatientAgeEdit.setText(patient.patient_age.toString())
        binding.editTextPatientSexEdit.setText(patient.patient_sex)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = BottomSheetFragmentBinding.bind(view)
        val patientId = arguments?.getInt("patient_id_key")
        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)

        viewModel.getPatient.observe(viewLifecycleOwner, observePatientInfo)
        if(patientId != null) {
            viewModel.getOnePatient(patientId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_fragment, container, false)
    }

}