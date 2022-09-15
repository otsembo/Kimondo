package com.otsembo.kimondo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otsembo.kimondo.data.model.Album
import com.otsembo.kimondo.data.model.Keyword
import com.otsembo.kimondo.data.model.SearchData

@Dao
interface SearchDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearchData(searchData: SearchData)

    @Update
    suspend fun updateSearchData(searchData: SearchData)

    @Delete
    suspend fun deleteSearchData(searchData: SearchData)

    @Query("SELECT * FROM search_data WHERE title LIKE :search_term")
    fun searchInfo(search_term: String): LiveData<List<SearchData>>
}

@Dao
interface AlbumDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: Album)

    @Update
    suspend fun updateAlbum(album: Album)

    @Delete
    suspend fun deleteAlbum(album: Album)

    @Query("SELECT * FROM albums WHERE searchData = :search_data_id")
    suspend fun fetchAlbums(search_data_id: Long): List<Album>

}

@Dao
interface KeywordsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeywords(keyword: Keyword)

    @Update
    suspend fun updateKeyword(keyword: Keyword)

    @Delete
    suspend fun deleteKeyword(keyword: Keyword)

    @Query("SELECT * FROM keywords WHERE searchData = :search_data")
    suspend fun fetchKeywords(search_data: Long): List<Keyword>

}