package com.otsembo.kimondo.data.repository

import androidx.lifecycle.LiveData
import com.otsembo.kimondo.data.db.dao.AlbumDao
import com.otsembo.kimondo.data.db.dao.KeywordsDao
import com.otsembo.kimondo.data.db.dao.SearchDataDao
import com.otsembo.kimondo.data.model.SearchData
import com.otsembo.kimondo.data.network.NasaService

class SearchRepository(
    private val searchDataDao: SearchDataDao,
    private val albumDao: AlbumDao,
    private val keywordsDao: KeywordsDao,
    private val nasaService: NasaService
) {

    suspend fun retrieveSearchResults(query: String){
        val nasaSearch = nasaService.searchNasa(query = query)
        nasaSearch.collection.items.forEach { item ->
            item.data.forEach { data ->
                val searchData = SearchData(
                    center = data.center,
                    date_created = data.date_created,
                    description = data.description,
                    description_508 = data.description_508,
                    location = data.location,
                    media_type = data.media_type,
                    nasa_id = data.nasa_id,
                    photographer = data.photographer,
                    secondary_creator = data.secondary_creator,
                    title = data.title
                )
                searchDataDao.addSearchData(searchData = searchData)
            }
        }
    }

    fun displaySearchData(query: String): LiveData<List<SearchData>> = searchDataDao.searchInfo(search_term = query)

}