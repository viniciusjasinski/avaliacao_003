package com.example.avaliacao3crudroomhilt.di

import android.content.Context
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
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
    fun provideContextDaoPatient(@ApplicationContext context: Context) : PatientDAO {
        return AppDatabase.getDatabase(context).patientDAO()
    }

    @Provides
    fun provideContextDaoSpecialty(@ApplicationContext context: Context) : SpecialtyDAO {
        return AppDatabase.getDatabase(context).specialtyDAO()
    }

    @Provides
    fun provideRepositoryPatient(patientDAO: PatientDAO, specialtyDAO: SpecialtyDAO) : DatabaseRepository = DatabaseRepository(patientDAO, specialtyDAO)


}