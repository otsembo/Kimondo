package com.otsembo.kimondo

import android.app.Application
import androidx.work.*
import com.otsembo.kimondo.data.work.DBCleanerWorker
import java.util.concurrent.TimeUnit

class KimondoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpWorkers()
    }

    private fun setUpWorkers(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val clearDBRequest = PeriodicWorkRequestBuilder<DBCleanerWorker>(
            1, TimeUnit.DAYS
        ).setConstraints(constraints).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("clear_db", ExistingPeriodicWorkPolicy.REPLACE, clearDBRequest)
    }

}