package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avaliacao3crudroomhilt.view_model.SpecialtyViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.adapter.SpecialtyAdapter
import com.example.avaliacao3crudroomhilt.databinding.SpecialtyFragmentBinding
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.example.avaliacao3crudroomhilt.utils.SpecialtyClickableItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialtyFragment : Fragment(R.layout.specialty_fragment), SpecialtyClickableItem {

    companion object {
        fun newInstance() = SpecialtyFragment()
    }

    private lateinit var viewModel: SpecialtyViewModel
    private lateinit var binding: SpecialtyFragmentBinding
    private val adapter = SpecialtyAdapter(this)

    private val observeSpecialties = Observer<List<SpecialtyModel>> { listOfSpecialties ->
         adapter.refresh(listOfSpecialties)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SpecialtyFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)

        binding.recyclerViewSpecialty.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSpecialty.adapter = adapter

        viewModel.specialtyList.observe(viewLifecycleOwner, observeSpecialties)

        getScreenEvents()
        viewModel.fetchSpecialties()


    }

    private fun getScreenEvents() {
        binding.buttonInsertSpecialty.setOnClickListener {
            val specialtyName = binding.editTextSpecialtyName.text.toString()
            if(specialtyName.isNotEmpty()) {
                viewModel.insertSpecialty(SpecialtyModel(specialty_name = specialtyName))
            }
        }
        binding.buttonShowSpecialtyList.setOnClickListener {
            if(binding.recyclerViewSpecialty.visibility == View.VISIBLE) {
                binding.recyclerViewSpecialty.visibility = View.INVISIBLE
            } else {
                binding.recyclerViewSpecialty.visibility = View.VISIBLE
            }
        }
    }


}