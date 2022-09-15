package com.otsembo.kimondo.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.data.repository.SearchRepository

class SearchVM (private val searchRepository: SearchRepository): ViewModel(){

    val latestSearch: MutableLiveData<List<SearchData>> = MutableLiveData(emptyList())

    suspend fun searchNasa(query: String){
        searchRepository.retrieveSearchResults(query = query)
    }

    fun updateSearchInfo(query: String){
        latestSearch.value = searchRepository.displaySearchData(query =  query).value
    }


}