package com.yusuf.personaltrainer

import android.app.Application
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.JsonFoodLoader
import com.yusuf.personaltrainer.data.workers.DailyResetWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import java.time.Duration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        scheduleDailyReset()

        // JSON Food Database Initializer
        val db = AppDatabase.getInstance(this)

        CoroutineScope(Dispatchers.IO).launch {

            val count = db.foodDao().getFoodCount()
            Log.d("JSON_DEBUG", "Before insert count = $count")

            if (count == 0) {

                val foods = JsonFoodLoader.loadFoodsFromJson(this@MyApplication)
                Log.d("JSON_DEBUG", "Loaded foods size = ${foods.size}")

                db.foodDao().insertAll(foods)

                val newCount = db.foodDao().getFoodCount()
                Log.d("JSON_DEBUG", "After insert count = $newCount")
            }
        }
    }

    private fun scheduleDailyReset() {

        val request =
            PeriodicWorkRequestBuilder<DailyResetWorker>(
                1, TimeUnit.DAYS
            )
                .setInitialDelay(
                    calculateInitialDelay(),
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "daily_reset_work",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

    private fun calculateInitialDelay(): Long {

        val now = ZonedDateTime.now()
        val nextMidnight =
            now.toLocalDate().plusDays(1).atStartOfDay(now.zone)

        return Duration.between(now, nextMidnight).toMillis()
    }



}


