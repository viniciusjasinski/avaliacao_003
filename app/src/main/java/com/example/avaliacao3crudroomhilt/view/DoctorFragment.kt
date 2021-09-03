package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.avaliacao3crudroomhilt.view_model.DoctorViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.DoctorFragmentBinding
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DoctorFragment : Fragment(R.layout.doctor_fragment) {

    companion object {
        fun newInstance() = DoctorFragment()
    }

    private lateinit var viewModel: DoctorViewModel
    private lateinit var binding: DoctorFragmentBinding

    private val observeDoctorsList = Observer<List<DoctorModel>> {

    }
    private val observeSpecialtyList = Observer<List<SpecialtyModel>> {
        chargeAutoComplete(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DoctorFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        viewModel.specialtyList.observe(viewLifecycleOwner, observeSpecialtyList)

        viewModel.fetchSpecialty()
        viewModel.fetchDoctors()


    }

    private fun chargeAutoComplete(specialtyModel: List<SpecialtyModel>) {
        val arr = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            specialtyModel
        )

        binding.autoCompleteSpecialty.setAdapter(arr)
        binding.autoCompleteSpecialty.setOnItemClickListener { adapterView, view, i, l ->
            adapterView.getItemAtPosition(i)
        }

    }

}