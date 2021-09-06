package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.*
import com.example.avaliacao3crudroomhilt.model.ScheduleModel
import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor

@Dao
interface ScheduleDAO {

    @Insert
    fun insertSchedule(scheduleModel: ScheduleModel)

    @Query("SELECT * FROM ScheduleModel")
    fun getAllSchedules(): List<SchedulePatientDoctor>

    @Query("SELECT * FROM ScheduleModel WHERE schedule_id = :scheduleId")
    fun getSchedule(scheduleId : Int): SchedulePatientDoctor

    @Delete
    fun deleteSchedule(scheduleModel: ScheduleModel)

    @Update
    fun updateSchedule(scheduleModel: ScheduleModel)

    @Transaction
    @Query("SELECT * FROM ScheduleModel, PatientModel WHERE patientIdFK = patient_id AND patient_sex LIKE '%' || :sex || '%'")
    fun filterBySex(sex: String): List<SchedulePatientDoctor>

    @Transaction
    @Query("SELECT * FROM ScheduleModel, DoctorModel, SpecialtyModel WHERE doctorIdFK = doctor_id AND doctor_specialtyFK = specialty_id AND specialty_name LIKE '%' || :specialty || '%'")
    fun filterBySpecialty(specialty: String): List<SchedulePatientDoctor>

}