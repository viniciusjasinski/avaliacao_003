package com.example.avaliacao3crudroomhilt.DaoTest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.DoctorDAO
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DoctorDAOTest {


    private lateinit var database: AppDatabase
    private lateinit var dao: DoctorDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.doctorDAO()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertDoctor_should_return_true() {
        val specialty = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        val doutorJaime = DoctorModel(doctor_id = 1, doctor_name = "Jaime Stapassoli", doctor_specialtyFK = 1)

        val doctorWithSpecialty = DoctorWithSpecialty(doutorJaime, specialty)
        dao.insertDoctorWithSpecialty(doctorWithSpecialty)
        assertThat(dao.getAllDoctors()).contains(doctorWithSpecialty)
    }

    @Test
    fun deleteDoctor_should_return_true() {
        val specialty = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        val doutorJaime = DoctorModel(doctor_id = 1, doctor_name = "Jaime Stapassoli", doctor_specialtyFK = 1)

        val doctorWithSpecialty = DoctorWithSpecialty(doutorJaime, specialty)
        dao.insertDoctorWithSpecialty(doctorWithSpecialty)
        dao.deleteDoctor(doutorJaime)
        assertThat(dao.getAllDoctors()).doesNotContain(doctorWithSpecialty)
    }

    @Test
    fun editDoctor_should_return_true() {
        val specialty = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        val doutorJaime = DoctorModel(doctor_id = 1, doctor_name = "Jaime Stapassoli", doctor_specialtyFK = 1)
        val doctorWithSpecialty = DoctorWithSpecialty(doutorJaime, specialty)

        dao.insertDoctorWithSpecialty(doctorWithSpecialty)
        dao.updateDoctor(DoctorModel(doctor_id = 1, doctor_name = "Botao Stapassoli", doctor_specialtyFK = 2))

        assertThat(dao.getAllDoctors()).doesNotContain(doctorWithSpecialty)

    }

}