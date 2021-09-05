package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.*
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

    @Update
    fun updatePatient(patientModel: PatientModel)

}