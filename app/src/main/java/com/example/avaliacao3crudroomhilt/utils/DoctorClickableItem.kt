package com.example.avaliacao3crudroomhilt.utils

import com.example.avaliacao3crudroomhilt.model.DoctorWithSpecialty

interface DoctorClickableItem {

    fun clickTrashIcon(doctorWithSpecialty: DoctorWithSpecialty)
    fun clickEditIcon(doctorWithSpecialty: DoctorWithSpecialty)

}