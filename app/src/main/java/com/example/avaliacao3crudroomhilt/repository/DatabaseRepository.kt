package com.example.avaliacao3crudroomhilt.repository

import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.model.PatientModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    val repository: PatientDAO
){

    fun insertNewPatient(patient: PatientModel) {
        repository.insertIntoPatients(patient)
    }

    fun getPatientsList() : List<PatientModel> {
        return repository.getAllPatients()
    }


}