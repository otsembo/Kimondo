package com.otsembo.kimondo.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.data.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchVM (private val searchRepository: SearchRepository): ViewModel(){

    val latestSearch: MutableLiveData<List<SearchData>> = MutableLiveData(emptyList())
    val query: MutableLiveData<String> = MutableLiveData("")

    fun searchNasa(){
        viewModelScope.launch {
            query.value?.let { value ->
                searchRepository.retrieveSearchResults(query = value)
            }
        }
    }

    fun updateSearchInfo(){
        query.value?.let { value ->
            latestSearch.value = searchRepository.displaySearchData(query =  value).value
        }
    }


}