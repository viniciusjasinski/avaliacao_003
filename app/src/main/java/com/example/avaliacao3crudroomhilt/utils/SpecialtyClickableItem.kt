package com.example.avaliacao3crudroomhilt.utils

import com.example.avaliacao3crudroomhilt.model.SpecialtyModel

interface SpecialtyClickableItem {

    fun clickTrashIcon(specialtyModel: SpecialtyModel)
    fun clickEditIcon(specialtyModel: SpecialtyModel)

}