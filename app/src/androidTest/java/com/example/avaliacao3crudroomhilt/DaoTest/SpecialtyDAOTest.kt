package com.example.avaliacao3crudroomhilt.DaoTest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.SpecialtyDAO
import com.example.avaliacao3crudroomhilt.model.SpecialtyModel
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SpecialtyDAOTest {


    private lateinit var database: AppDatabase
    private lateinit var dao: SpecialtyDAO

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.specialtyDAO()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertSpecialtyDatabase_should_return_true() {

        val specialtyExample = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        dao.insertIntoSpecialty(specialtyExample)
        val getSpecialty = dao.getSpecialty(1)

        Truth.assertThat(getSpecialty).isEqualTo(specialtyExample)
    }

    @Test
    fun getAllSpecialtiesDatabase_should_return_true() {

        val specialtyExample = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        val specialtyExample2 = SpecialtyModel(specialty_id = 2, specialty_name = "Pediatra")
        dao.insertIntoSpecialty(specialtyExample)
        dao.insertIntoSpecialty(specialtyExample2)
        val getAllSpecialties = dao.getAllSpecialties()

        Truth.assertThat(getAllSpecialties).contains(specialtyExample)
    }

    @Test
    fun deletePatientDatabase_should_return_true() {

        val specialtyExample = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        dao.insertIntoSpecialty(specialtyExample)
        dao.deleteSpecialty(specialtyExample)
        val getAllSpecialties = dao.getAllSpecialties()

        Truth.assertThat(getAllSpecialties).doesNotContain(specialtyExample)
    }

    @Test
    fun editPatientDatabase_should_return_true() {
        val specialtyExample = SpecialtyModel(specialty_id = 1, specialty_name = "Ortopedista")
        dao.insertIntoSpecialty(specialtyExample)
        dao.updateSpecialty(specialtyExample.specialty_id, specialtyName = "Pediatra")

        Truth.assertThat(dao.getAllSpecialties()).doesNotContain(specialtyExample)
    }

}