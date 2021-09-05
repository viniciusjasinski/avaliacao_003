package com.example.avaliacao3crudroomhilt.repository

import com.example.avaliacao3crudroomhilt.database.dao.DoctorDAO
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.database.dao.ScheduleDAO
import com.example.avaliacao3crudroomhilt.database.dao.SpecialtyDAO
import com.example.avaliacao3crudroomhilt.model.*
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val patientRepository: PatientDAO,
    private val specialtyRepository: SpecialtyDAO,
    private val doctorRepository: DoctorDAO,
    private val scheduleRepository: ScheduleDAO
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
        patientRepository.updatePatient(patientModel)
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

    fun updateSpecialty(specialtyModel: SpecialtyModel) {
        specialtyRepository.updateSpecialty(
            specialtyModel.specialty_id,
            specialtyModel.specialty_name
        )
    }

    ////////////////////// Doctor //////////////////////////
    fun insertNewDoctor(doctorModel: DoctorModel) {
        doctorRepository.insertIntoDoctor(doctorModel)
    }

    fun getDoctorsList(): List<DoctorWithSpecialty> {
        return doctorRepository.getAllDoctors()
    }

    fun deleteDoctor(doctorModel: DoctorModel) {
        doctorRepository.deleteDoctor(doctorModel)
    }

    fun getSpecificDoctor(doctorId: Int): DoctorWithSpecialty {
        return doctorRepository.getDoctor(doctorId)
    }

    fun updateDoctor(doctorModel: DoctorModel) {
        doctorRepository.updateDoctor(doctorModel)
    }

    ////////////////////// Schedule //////////////////////////
    fun insertNewSchedule(scheduleModel: ScheduleModel) {
        scheduleRepository.insertSchedule(scheduleModel)
    }

    fun getScheduleList(): List<SchedulePatientDoctor> {
        return scheduleRepository.getAllSchedules()
    }

    fun deleteSchedule(scheduleModel: ScheduleModel) {
        scheduleRepository.deleteSchedule(scheduleModel)
    }

    fun getSpecificSchedule(scheduleId: Int): SchedulePatientDoctor {
        return scheduleRepository.getSchedule(scheduleId)
    }

    fun updateSchedule(scheduleModel: ScheduleModel) {
        scheduleRepository.updateSchedule(scheduleModel)
    }

}