package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.model.*
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

    private val _schedule = MutableLiveData<SchedulePatientDoctor>()
    val schedule: LiveData<SchedulePatientDoctor> = _schedule


    private val _doctorsList = MutableLiveData<List<DoctorWithSpecialty>>()
    val doctorsList: LiveData<List<DoctorWithSpecialty>> = _doctorsList

    private val _patientsList = MutableLiveData<List<PatientModel>>()
    val patientsList: LiveData<List<PatientModel>> = _patientsList


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

    fun getOneSchedule(scheduleId: Int) {
        _schedule.value = repository.getSpecificSchedule(scheduleId)
    }

    fun getAllDoctors() {
        _doctorsList.value = repository.getDoctorsList()
    }

    fun getAllPatients() {
        _patientsList.value = repository.getPatientsList()
    }

    fun updateSchedule(scheduleModel: ScheduleModel) {
        repository.updateSchedule(scheduleModel)
    }

}