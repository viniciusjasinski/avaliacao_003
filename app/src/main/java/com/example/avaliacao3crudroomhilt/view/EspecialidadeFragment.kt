package com.example.avaliacao3crudroomhilt.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.avaliacao3crudroomhilt.view_model.EspecialidadeViewModel
import com.example.avaliacao3crudroomhilt.R

class EspecialidadeFragment : Fragment(R.layout.especialidade_fragment) {

    companion object {
        fun newInstance() = EspecialidadeFragment()
    }

    private lateinit var viewModel: EspecialidadeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EspecialidadeViewModel::class.java)
    }


}