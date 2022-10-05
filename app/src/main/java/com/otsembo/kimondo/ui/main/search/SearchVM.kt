package com.otsembo.kimondo.ui.main.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.data.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchVM (private val searchRepository: SearchRepository): ViewModel(){

    val latestSearch = searchRepository.displaySearchData()
    val query: MutableStateFlow<String> = MutableStateFlow("")
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun searchNasa(){
        viewModelScope.launch {
            toggleLoading(true)
            Log.d("TAG", "searchNasa: Loading Start")
            searchRepository.retrieveSearchResults(query = query.value)
            toggleLoading(false)
        }
    }

    private fun toggleLoading(status: Boolean){
        isLoading.value = status
    }


}