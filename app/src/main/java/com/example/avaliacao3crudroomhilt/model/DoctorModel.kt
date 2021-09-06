package com.example.avaliacao3crudroomhilt.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class DoctorModel(
    @PrimaryKey(autoGenerate = true)
    var doctor_id: Int = 0,
    val doctor_name: String,
    val doctor_specialtyFK : Int
)

data class DoctorWithSpecialty(
    @Embedded
    val doctor: DoctorModel?,
    @Relation(
        parentColumn = "doctor_specialtyFK",
        entityColumn = "specialty_id"
    )
    val specialty: SpecialtyModel?
) {
    override fun toString(): String {
        return "${doctor!!.doctor_name}, ${specialty!!.specialty_name}"
    }
}