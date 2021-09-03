package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.avaliacao3crudroomhilt.model.DoctorModel

@Dao
interface DoctorDAO {

    @Insert
    fun insertIntoDoctor(doctorModel: DoctorModel)

    @Query("SELECT * FROM DoctorModel")
    fun getAllDoctors() : List<DoctorModel>

    @Delete
    fun deleteDoctor(doctorModel: DoctorModel)

    @Query("SELECT * FROM DoctorModel WHERE doctor_id = :doctorId")
    fun getDoctor(doctorId: Int) : DoctorModel

    @Query("UPDATE DoctorModel SET doctor_name = :doctorName, doctor_specialtyFK = :doctorSpecialtyFK WHERE doctor_id = :doctorId")
    fun updateDoctor(doctorId: Int, doctorName: String, doctorSpecialtyFK: Int)


}