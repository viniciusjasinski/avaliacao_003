package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    val repository: DatabaseRepository
) : ViewModel() {

    private val _patient = MutableLiveData<PatientModel>()
    val patient: LiveData<PatientModel> = _patient

    private val _specialty = MutableLiveData<SpecialtyModel>()
    val specialty: LiveData<SpecialtyModel> = _specialty

    fun getOnePatient(patientId: Int) {
        _patient.value = repository.getSpecificPatient(patientId)
    }

    fun updatePatient(patientModel: PatientModel) {
        repository.updatePatient(patientModel)
    }

    fun getOneSpecialty(specialtyId: Int) {
        _specialty.value = repository.getSpecificSpecialty(specialtyId)
    }

    fun updateSpecialty(specialtyModel: SpecialtyModel) {
        repository.updateSpecialty(specialtyModel)
    }

}