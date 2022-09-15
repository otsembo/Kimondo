package com.otsembo.kimondo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otsembo.kimondo.data.db.dao.*
import com.otsembo.kimondo.data.model.*

@Database(entities = [Apod::class, NearEarthObject::class, RoverPhoto::class, SearchData::class, Album::class, Keyword::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao

    abstract fun neoDao(): NearEarthObjectDao

    abstract fun roverPhotoDao(): RoverPhotoDao

    abstract fun searchDataDao(): SearchDataDao

    abstract fun albumDao(): AlbumDao

    abstract fun keywordDao(): KeywordsDao

    companion object {

        @Volatile
        private var DB: AppDatabase? = null

        fun getDB(context: Context): AppDatabase{
            return DB ?: synchronized(this){
                DB ?: Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "kimondo"
                ).fallbackToDestructiveMigration()
                    .enableMultiInstanceInvalidation()
                    .build()
                    .also { appDatabase -> DB = appDatabase }
            }
        }

    }

}