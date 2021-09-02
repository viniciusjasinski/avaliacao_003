package com.example.avaliacao3crudroomhilt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.model.PatientModel

@Database(entities = [PatientModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun patientDAO() : PatientDAO

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