package com.example.avaliacao3crudroomhilt

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EspecialidadeFragment : Fragment() {

    companion object {
        fun newInstance() = EspecialidadeFragment()
    }

    private lateinit var viewModel: EspecialidadeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.especialidade_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EspecialidadeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}