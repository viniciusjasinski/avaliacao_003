package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    val repository: DatabaseRepository
) : ViewModel() {

    private val _getPatient = MutableLiveData<PatientModel>()
    val getPatient: LiveData<PatientModel> = _getPatient

    fun getOnePatient(patientId: Int) {
        _getPatient.value = repository.getSpecificPatient(patientId)
    }
}