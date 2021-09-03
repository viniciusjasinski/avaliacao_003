package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {

    private val _doctorList = MutableLiveData<List<SpecialtyModel>>()
    val doctorList : LiveData<List<SpecialtyModel>> = _doctorList

    private val _specialtyList = MutableLiveData<List<SpecialtyModel>>()
    val specialtyList : LiveData<List<SpecialtyModel>> = _specialtyList


    fun insertDoctor(doctorModel: DoctorModel) {
        repository.insertNewDoctor(doctorModel)
        fetchDoctors()
    }

    fun fetchDoctors() {
        _doctorList.value = repository.getSpecialtiesList()
    }

    fun fetchSpecialty() {
        _specialtyList.value = repository.getSpecialtiesList()
    }

    fun deleteDoctor(doctorModel: DoctorModel) {
        repository.deleteDoctor(doctorModel)
        fetchDoctors()
    }
}