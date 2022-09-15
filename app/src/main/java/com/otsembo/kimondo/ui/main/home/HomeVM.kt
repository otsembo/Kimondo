package com.otsembo.kimondo.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.data.repository.APODRepository
import kotlinx.coroutines.launch

class HomeVM(private val apodRepository: APODRepository) : ViewModel() {
    // retrieve latest apod from db
    val apod = apodRepository.getLatestApod()

    init {
        viewModelScope.launch {
            // fetch latest from network
            apodRepository.fetchFromNetwork()
        }
    }

}