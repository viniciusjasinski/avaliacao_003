package com.example.avaliacao3crudroomhilt.utils

import com.example.avaliacao3crudroomhilt.model.SchedulePatientDoctor


interface ScheduleClickableItem {

    fun clickTrashIcon(schedulePatientDoctor: SchedulePatientDoctor)
    fun clickEditIcon(schedulePatientDoctor: SchedulePatientDoctor)

}