package com.example.avaliacao3crudroomhilt.DaoTest

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PatientDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: PatientDAO

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.patientDAO()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertPatientDatabase_should_return_true() {

        val patientExample = PatientModel(1, "Alberto", 51, "Masculino")
        dao.insertIntoPatients(patientExample)
        val getAllPatients = dao.getAllPatients()

        assertThat(getAllPatients).contains(patientExample)
    }

    @Test
    fun deletePatientDatabase_should_return_true() {
        val patientExample = PatientModel(1, "Alberto", 51, "Masculino")
        dao.insertIntoPatients(patientExample)
        dao.deletePatient(patientExample)
        val getAllPatients = dao.getAllPatients()

        assertThat(getAllPatients).doesNotContain(patientExample)
    }

    @Test
    fun editPatientDatabase_should_return_true() {
        val patientExample = PatientModel(1, "Alberto", 51, "Masculino")
        val patientExample2 = PatientModel(1, "Roberto", 51, "Masculino")
        dao.insertIntoPatients(patientExample)
        dao.updatePatient(patientExample2)

        assertThat(dao.getAllPatients()).contains(patientExample2)
    }

}