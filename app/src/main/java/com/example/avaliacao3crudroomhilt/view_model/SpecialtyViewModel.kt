package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtyViewModel @Inject constructor(
    val repository: DatabaseRepository
) : ViewModel() {

    private val _specialtyList = MutableLiveData<List<SpecialtyModel>>()
    val specialtyList : LiveData<List<SpecialtyModel>> = _specialtyList

    fun insertSpecialty(specialtyModel: SpecialtyModel) {
        repository.insertNewSpecialty(specialtyModel)
        fetchSpecialties()
    }

    fun fetchSpecialties() {
        _specialtyList.value = repository.getSpecialtiesList()
    }

    fun deleteSpecialty(specialtyModel: SpecialtyModel) {
        repository.deleteSpecialty(specialtyModel)
        fetchSpecialties()
    }



}