package com.otsembo.kimondo.ui.main.marsrover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.data.repository.MarsPhotosRepository
import kotlinx.coroutines.launch

class MarsRoverVM(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {

    val marsPhotos = marsPhotosRepository.displayPhotos()

    init {
        viewModelScope.launch {
            marsPhotosRepository.retrieveMarsPhotos()
        }
    }

}