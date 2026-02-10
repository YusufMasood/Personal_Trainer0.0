package com.yusuf.personaltrainer.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yusuf.personaltrainer.data.local.AppDatabase
import java.time.LocalDate

class DailyResetWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val dao = AppDatabase
            .getInstance(applicationContext)
            .dailyNutritionDao()

        val today = LocalDate.now().toString()
        dao.resetToday(today)

        return Result.success()
    }
}