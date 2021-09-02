package com.example.avaliacao3crudroomhilt.utils

import com.example.avaliacao3crudroomhilt.model.PatientModel

interface PatientClickableItem {

    fun clickTrashIcon(patientModel: PatientModel)

    fun clickEditIcon(patientModel: PatientModel)

}