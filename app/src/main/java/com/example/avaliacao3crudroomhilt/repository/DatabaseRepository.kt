package com.example.avaliacao3crudroomhilt.repository

import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.model.PatientModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    val repository: PatientDAO
) {

    fun insertNewPatient(patientModel: PatientModel) {
        repository.insertIntoPatients(patientModel)
    }

    fun getPatientsList(): List<PatientModel> {
        return repository.getAllPatients()
    }

    fun deletePatient(patientModel: PatientModel) {
        repository.deletePatient(patientModel)
    }

    fun getSpecificPatient(patientId: Int): PatientModel {
        return repository.getPatient(patientId)
    }

    fun updatePatient(patientModel: PatientModel) {
        repository.updatePatient(
            patientModel.patient_id,
            patientModel.patient_name,
            patientModel.patient_age,
            patientModel.patient_sex
        )
    }

}