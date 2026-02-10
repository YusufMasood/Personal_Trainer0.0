package com.yusuf.personaltrainer

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.yusuf.personaltrainer.data.workers.DailyResetWorker
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import java.time.Duration


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        scheduleDailyReset()
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
