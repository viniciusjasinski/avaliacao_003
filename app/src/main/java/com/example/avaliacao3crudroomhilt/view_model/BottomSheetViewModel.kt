package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
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

    private val _doctorWithSpecialty = MutableLiveData<DoctorWithSpecialty>()
    val doctorWithSpecialty: LiveData<DoctorWithSpecialty> = _doctorWithSpecialty

    private val _specialtyList = MutableLiveData<List<SpecialtyModel>>()
    val specialtyList: LiveData<List<SpecialtyModel>> = _specialtyList
    fun getOnePatient(patientId: Int) {
        _patient.value = repository.getSpecificPatient(patientId)
    }

    fun updatePatient(patientModel: PatientModel) {
        repository.updatePatient(patientModel)
    }

    fun getOneSpecialty(specialtyId: Int) {
        _specialty.value = repository.getSpecificSpecialty(specialtyId)
    }

    fun getAllSpecialty() {
        _specialtyList.value = repository.getSpecialtiesList()
    }

    fun updateSpecialty(specialtyModel: SpecialtyModel) {
        repository.updateSpecialty(specialtyModel)
    }

    fun getOneDoctorWithSpecialty(doctorId: Int) {
        _doctorWithSpecialty.value = repository.getSpecificDoctor(doctorId)
    }

    fun updateDoctor(doctorModel: DoctorModel) {
       repository.updateDoctor(doctorModel)
    }


}