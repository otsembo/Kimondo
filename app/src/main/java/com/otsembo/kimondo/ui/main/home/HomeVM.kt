package com.otsembo.kimondo.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.common.AppResource
import com.otsembo.kimondo.data.model.Apod
import com.otsembo.kimondo.data.repository.APODRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeVM(private val apodRepository: APODRepository) : ViewModel() {

    private val _apodStatus = MutableStateFlow<AppResource<Apod>>(AppResource.Idle())
    val apodStatus: StateFlow<AppResource<Apod>> = _apodStatus

    // retrieve latest apod from db
    val apod = apodRepository.getLatestApod()

    init {
        viewModelScope.launch {
            // fetch latest from network
            try {
                _apodStatus.emit(AppResource.Loading())
                val apod = apodRepository.fetchFromNetwork()
                _apodStatus.emit(AppResource.Success(data = apod))
            }catch (e: Exception){
                _apodStatus.emit(AppResource.Error(message = e.localizedMessage?: CONNECTION_ERROR))
            }

        }
    }


    companion object {
        const val CONNECTION_ERROR = "Something went wrong while fetching data. Please try again later."
    }


}