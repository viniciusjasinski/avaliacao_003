package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel

@Dao
interface SpecialtyDAO {

    @Insert
    fun insertIntoSpecialty(specialtyModel: SpecialtyModel)

    @Query("SELECT * FROM SpecialtyModel")
    fun getAllSpecialties() : List<SpecialtyModel>

    @Delete
    fun deleteSpecialty(specialtyModel: SpecialtyModel)

    @Query("SELECT * FROM SpecialtyModel WHERE specialty_id = :specialtyId")
    fun getSpecialty(specialtyId: Int) : SpecialtyModel

    @Query("UPDATE SpecialtyModel SET specialty_name = :specialtyName WHERE specialty_id = :specialtyId")
    fun updateSpecialty(specialtyId: Int, specialtyName: String)


}