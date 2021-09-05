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

}