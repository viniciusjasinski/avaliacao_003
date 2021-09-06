package com.example.avaliacao3crudroomhilt.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PatientModel(
    @PrimaryKey(autoGenerate = true)
    var patient_id: Int = 0,
    val patient_name: String,
    val patient_age: Int,
    val patient_sex: String
) {
    override fun toString(): String {
        return "$patient_name, $patient_age, $patient_sex"
    }
}
