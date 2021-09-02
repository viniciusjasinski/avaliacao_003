package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.avaliacao3crudroomhilt.model.PatientModel

@Dao
interface PatientDAO {

    @Insert
    fun insertIntoPatients(patientModel: PatientModel)

    @Query("SELECT * FROM PatientModel")
    fun getAllPatients() : List<PatientModel>

    @Delete
    fun deletePatient(patientModel: PatientModel)

    @Query("SELECT * FROM PatientModel WHERE patient_id = :patientId")
    fun getPatient(patientId: Int) : PatientModel

    @Query("UPDATE PatientModel SET patient_name = :patientName, patient_age = :patientAge, patient_sex = :patientSex WHERE patient_id = :patientId")
    fun updatePatient(patientId: Int, patientName: String, patientAge: Int, patientSex: String)

}