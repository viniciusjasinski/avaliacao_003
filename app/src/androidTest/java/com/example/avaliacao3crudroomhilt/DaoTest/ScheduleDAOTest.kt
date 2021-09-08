package com.example.avaliacao3crudroomhilt.DaoTest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.DoctorDAO
import com.example.avaliacao3crudroomhilt.database.dao.PatientDAO
import com.example.avaliacao3crudroomhilt.database.dao.ScheduleDAO
import com.example.avaliacao3crudroomhilt.database.dao.SpecialtyDAO
import com.example.avaliacao3crudroomhilt.model.*
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ScheduleDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ScheduleDAO
    private lateinit var daoPatient: PatientDAO
    private lateinit var daoDoctor: DoctorDAO
    private lateinit var daoSpecialty: SpecialtyDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()

        dao = database.scheduleDAO()
        daoPatient = database.patientDAO()
        daoDoctor = database.doctorDAO()
        daoSpecialty = database.specialtyDAO()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertSchedule_should_return_true() {
        val schedule = ScheduleModel(schedule_id = 1,doctorIdFK = 1, patientIdFK = 1)
        dao.insertSchedule(schedule)

        assertThat(dao.getSchedule(1).scheduleModel).isEqualTo(schedule)
    }

    @Test
    fun getAllSchedules_should_return_true() {
        val schedule = ScheduleModel(schedule_id = 1,doctorIdFK = 1, patientIdFK = 1)
        val schedule2 = ScheduleModel(schedule_id = 2,doctorIdFK = 1, patientIdFK = 1)
        dao.insertSchedule(schedule)
        dao.insertSchedule(schedule2)

        assertThat(dao.getAllSchedules().size).isEqualTo(2)
    }

    @Test
    fun deleteSchedule_should_return_true() {
        val schedule = ScheduleModel(schedule_id = 1,doctorIdFK = 1, patientIdFK = 1)
        dao.insertSchedule(schedule)
        dao.deleteSchedule(schedule)

        assertThat(dao.getSchedule(1)).isNull()
    }

    @Test
    fun updateSchedule_should_return_true() {
        val schedule = ScheduleModel(schedule_id = 1,doctorIdFK = 1, patientIdFK = 1)
        dao.insertSchedule(schedule)
        dao.updateSchedule(ScheduleModel(schedule_id = 1,doctorIdFK = 2, patientIdFK = 1))

        assertThat(dao.getSchedule(1).scheduleModel).isNotEqualTo(schedule)
    }

    @Test
    fun filterScheduleBySex_should_return_true() {
        val patientExample = PatientModel(1, "Alberto", 51, "Feminino")
        val patientExample2 = PatientModel(2, "Roberto", 51, "Masculino")
        val schedule = ScheduleModel(schedule_id = 1,doctorIdFK = 1, patientIdFK = 1)
        val schedule2 = ScheduleModel(schedule_id = 2,doctorIdFK = 1, patientIdFK = 2)
        daoPatient.insertIntoPatients(patientExample)
        daoPatient.insertIntoPatients(patientExample2)
        dao.insertSchedule(schedule)
        dao.insertSchedule(schedule2)

//        assertThat(dao.filterBySex("Masculino")).doesNotContain(SchedulePatientDoctor(schedule, null, patientExample))
        assertThat(dao.filterBySex("Masculino")).contains(SchedulePatientDoctor(schedule2, null, patientExample2))
    }

    @Test
    fun filterScheduleBySpecialty_should_return_true() {
        val specialtyExample = SpecialtyModel(1, "Ortopedista")
        val specialtyExample2 = SpecialtyModel(2, "Cardiologista")
        val doutorJaime = DoctorModel(doctor_id = 1, doctor_name = "Jaime Stapassoli", doctor_specialtyFK = 1)
        val doutorJaime2 = DoctorModel(doctor_id = 2, doctor_name = "Jaiminho Silva", doctor_specialtyFK = 2)
        val schedule = ScheduleModel(schedule_id = 1,doctorIdFK = 1, patientIdFK = 1)
        val schedule2 = ScheduleModel(schedule_id = 2,doctorIdFK = 2, patientIdFK = 1)
        daoSpecialty.insertIntoSpecialty(specialtyExample)
        daoSpecialty.insertIntoSpecialty(specialtyExample2)
        daoDoctor.insertIntoDoctor(doutorJaime)
        daoDoctor.insertIntoDoctor(doutorJaime2)
        dao.insertSchedule(schedule)
        dao.insertSchedule(schedule2)

//        assertThat(dao.filterBySpecialty("Ortopedista")).doesNotContain(SchedulePatientDoctor(schedule, doutorJaime2,null))
        assertThat(dao.filterBySpecialty("Ortopedista")).contains(SchedulePatientDoctor(schedule, doutorJaime,null))
    }

}
