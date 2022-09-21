package com.otsembo.kimondo.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.data.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchVM (private val searchRepository: SearchRepository): ViewModel(){

    val latestSearch = searchRepository.displaySearchData()
    val query: MutableLiveData<String> = MutableLiveData("")
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun searchNasa(){
        viewModelScope.launch {
            toggleLoading()
            query.value?.let { value ->
                searchRepository.retrieveSearchResults(query = value)
            }
            toggleLoading()
        }
    }

    private fun toggleLoading(){
        isLoading.value?.let { currentLoadingStatus ->
            isLoading.value = !currentLoadingStatus
        }
    }


}