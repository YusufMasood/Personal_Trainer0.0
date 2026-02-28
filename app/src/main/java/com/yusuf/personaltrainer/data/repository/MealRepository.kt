package com.yusuf.personaltrainer.data.repository

import com.yusuf.personaltrainer.data.local.dao.MealEntryDao
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
import kotlinx.coroutines.flow.Flow

class MealRepository(
    private val dao: MealEntryDao
) {

    suspend fun insert(entry: MealEntryEntity) {
        dao.insert(entry)
    }

    fun getEntriesByDate(date: String): Flow<List<MealEntryEntity>> {
        return dao.getEntriesByDate(date)
    }
}