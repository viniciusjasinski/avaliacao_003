package com.example.avaliacao3crudroomhilt.repository

import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.database.dao.SpecialtyDAO
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    val patientRepository: PatientDAO,
    val specialtyRepository: SpecialtyDAO
) {

    /////////////////////// Patient ////////////////////////
    fun insertNewPatient(patientModel: PatientModel) {
        patientRepository.insertIntoPatients(patientModel)
    }

    fun getPatientsList(): List<PatientModel> {
        return patientRepository.getAllPatients()
    }

    fun deletePatient(patientModel: PatientModel) {
        patientRepository.deletePatient(patientModel)
    }

    fun getSpecificPatient(patientId: Int): PatientModel {
        return patientRepository.getPatient(patientId)
    }

    fun updatePatient(patientModel: PatientModel) {
        patientRepository.updatePatient(
            patientModel.patient_id,
            patientModel.patient_name,
            patientModel.patient_age,
            patientModel.patient_sex
        )
    }

    ///////////////////// Specialty /////////////////////////
    fun insertNewSpecialty(specialtyModel: SpecialtyModel) {
        specialtyRepository.insertIntoSpecialty(specialtyModel)
    }

    fun getSpecialtiesList(): List<SpecialtyModel> {
        return specialtyRepository.getAllSpecialties()
    }

    fun deleteSpecialty(specialtyModel: SpecialtyModel) {
        specialtyRepository.deleteSpecialty(specialtyModel)
    }

    fun getSpecificSpecialty(specialtyId: Int): SpecialtyModel {
        return specialtyRepository.getSpecialty(specialtyId)
    }

    fun updateSpecialty(SpecialtyModel: SpecialtyModel) {
        specialtyRepository.updateSpecialty(
            SpecialtyModel.specialty_id,
            SpecialtyModel.specialty_name
        )
    }

}