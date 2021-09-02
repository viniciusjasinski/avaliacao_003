package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.avaliacao3crudroomhilt.model.PatientModel

@Dao
interface PatientDAO {

    @Insert
    fun insertIntoPatients(patientModel: PatientModel)

    @Query("SELECT * FROM PatientModel")
    fun getAllPatients() : List<PatientModel>

}