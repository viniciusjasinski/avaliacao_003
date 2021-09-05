package com.example.avaliacao3crudroomhilt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.avaliacao3crudroomhilt.database.dao.DoctorDAO
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.database.dao.ScheduleDAO
import com.example.avaliacao3crudroomhilt.database.dao.SpecialtyDAO
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.ScheduleModel
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel

@Database(entities = [PatientModel::class, SpecialtyModel::class, DoctorModel::class, ScheduleModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun patientDAO() : PatientDAO

    abstract fun specialtyDAO() : SpecialtyDAO

    abstract fun doctorDAO() : DoctorDAO

    abstract fun scheduleDAO() : ScheduleDAO

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "consultas_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}