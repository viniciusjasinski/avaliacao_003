package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.*
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel

@Dao
interface DoctorDAO {

    @Insert
    fun insertIntoDoctor(doctorModel: DoctorModel)

    @Insert
    fun insertIntoSpecialty(specialtyModel: SpecialtyModel)

    @Insert
    fun insertDoctorWithSpecialty(doctorWithSpecialty: DoctorWithSpecialty) {
        doctorWithSpecialty.specialty?.let {
            insertIntoSpecialty(it)
        }
        doctorWithSpecialty.doctor?.let {
            insertIntoDoctor(it)
        }
    }

    @Query("SELECT * FROM DoctorModel")
    fun getAllDoctors() : List<DoctorWithSpecialty>

    @Delete
    fun deleteDoctor(doctorModel: DoctorModel)

    @Query("SELECT * FROM DoctorModel WHERE doctor_id = :doctorId")
    fun getDoctor(doctorId: Int) : DoctorWithSpecialty

    @Update
    fun updateDoctor(doctorModel: DoctorModel)


}