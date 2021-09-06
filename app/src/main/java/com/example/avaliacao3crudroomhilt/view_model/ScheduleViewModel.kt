package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.ScheduleModel
import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    val repository: DatabaseRepository
) : ViewModel() {

    private val _allSchedules = MutableLiveData<List<SchedulePatientDoctor>>()
    val allSchedules: LiveData<List<SchedulePatientDoctor>> = _allSchedules

    private val _allPatients = MutableLiveData<List<PatientModel>>()
    val allPatients: LiveData<List<PatientModel>> = _allPatients

    private val _allDoctors = MutableLiveData<List<DoctorWithSpecialty>>()
    val allDoctors: LiveData<List<DoctorWithSpecialty>> = _allDoctors

    fun insertSchedule(scheduleModel: ScheduleModel) {
        repository.insertNewSchedule(scheduleModel)
        fetchAllSchedules()
    }

    fun fetchAllSchedules() {
        _allSchedules.value = repository.getScheduleList()
    }

    fun deleteSchedule(scheduleModel: ScheduleModel) {
        repository.deleteSchedule(scheduleModel)
        fetchAllSchedules()
    }

    fun fetchAllPatients() {
        _allPatients.value = repository.getPatientsList()
    }

    fun fetchAllDoctors() {
        _allDoctors.value = repository.getDoctorsList()
    }

}