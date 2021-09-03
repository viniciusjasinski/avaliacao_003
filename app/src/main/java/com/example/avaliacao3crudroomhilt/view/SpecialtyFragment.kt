package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.avaliacao3crudroomhilt.view_model.SpecialtyViewModel
import com.example.avaliacao3crudroomhilt.R
import com.example.avaliacao3crudroomhilt.databinding.SpecialtyFragmentBinding

class SpecialtyFragment : Fragment(R.layout.specialty_fragment) {

    companion object {
        fun newInstance() = SpecialtyFragment()
    }

    private lateinit var viewModel: SpecialtyViewModel
    private lateinit var binding: SpecialtyFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SpecialtyFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)



    }


}