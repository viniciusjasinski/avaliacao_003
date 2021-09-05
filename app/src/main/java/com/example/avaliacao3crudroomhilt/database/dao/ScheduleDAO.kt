package com.example.avaliacao3crudroomhilt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.avaliacao3crudroomhilt.model.ScheduleModel
import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor

@Dao
interface ScheduleDAO {

    @Insert
    fun insertSchedule(scheduleModel: ScheduleModel)

    @Query("SELECT * FROM ScheduleModel")
    fun getAllSchedules(): List<SchedulePatientDoctor>

    @Delete
    fun deleteSchedule(scheduleModel: ScheduleModel)

}