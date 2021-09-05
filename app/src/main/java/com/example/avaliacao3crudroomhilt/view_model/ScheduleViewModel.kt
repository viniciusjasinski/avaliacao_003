package com.example.avaliacao3crudroomhilt.view_model

import androidx.lifecycle.ViewModel
import com.example.avaliacao3crudroomhilt.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    val repository: DatabaseRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}