package com.example.avaliacao3crudroomhilt.DaoTest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.avaliacao3crudroomhilt.database.AppDatabase
import com.example.avaliacao3crudroomhilt.database.dao.ScheduleDAO
import com.example.avaliacao3crudroomhilt.model.DoctorModel
import com.example.avaliacao3crudroomhilt.model.PatientModel
import com.example.avaliacao3crudroomhilt.model.ScheduleModel
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

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()

        dao = database.scheduleDAO()
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

}
