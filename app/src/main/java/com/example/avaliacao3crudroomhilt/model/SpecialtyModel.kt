package com.example.avaliacao3crudroomhilt.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpecialtyModel(
    @PrimaryKey(autoGenerate = true)
    var specialty_id: Int = 0,
    val specialty_name: String

)
