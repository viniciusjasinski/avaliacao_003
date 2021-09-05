package com.example.avaliacao3crudroomhilt.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class ScheduleModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val doctorIdFK: Int,
    val patientIdFK: Int

)

data class SchedulePatientDoctor(

    @Embedded
    val scheduleModel: ScheduleModel?,
    @Relation(
        parentColumn = "doctorIdFK",
        entityColumn = "doctor_id"
    )
    val doctorModel: DoctorModel?,

    @Relation(
        parentColumn = "patientIdFK",
        entityColumn = "patient_id"
    )
    val patientModel: PatientModel?

)
