package com.example.avaliacao3crudroomhilt.di

import android.content.Context
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.DoctorDAO
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.database.dao.ScheduleDAO
import com.example.avaliacao3crudroomhilt.database.dao.SpecialtyDAO
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun provideContextDaoPatient(@ApplicationContext context: Context): PatientDAO {
        return AppDatabase.getDatabase(context).patientDAO()
    }

    @Provides
    fun provideContextDaoSpecialty(@ApplicationContext context: Context): SpecialtyDAO {
        return AppDatabase.getDatabase(context).specialtyDAO()
    }

    @Provides
    fun provideContextDaoDoctor(@ApplicationContext context: Context): DoctorDAO {
        return AppDatabase.getDatabase(context).doctorDAO()
    }

    @Provides
    fun provideContextDaoSchedule(@ApplicationContext context: Context): ScheduleDAO {
        return AppDatabase.getDatabase(context).scheduleDAO()
    }

    @Provides
    fun provideRepository(
        patientDAO: PatientDAO,
        specialtyDAO: SpecialtyDAO,
        doctorDAO: DoctorDAO,
        scheduleDAO: ScheduleDAO
    ): DatabaseRepository = DatabaseRepository(patientDAO, specialtyDAO, doctorDAO, scheduleDAO)

}