package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(
    val repository: DatabaseRepository
) : ViewModel() {

    private val _patientList = MutableLiveData<List<PatientModel>>()
    val patientList: LiveData<List<PatientModel>> = _patientList

    fun insertPatient(patientModel: PatientModel) {
        repository.insertNewPatient(patientModel)
        _patientList.value = repository.getPatientsList()
    }

    fun fetchAllPatientsList() {
        _patientList.value = repository.getPatientsList()
    }


}