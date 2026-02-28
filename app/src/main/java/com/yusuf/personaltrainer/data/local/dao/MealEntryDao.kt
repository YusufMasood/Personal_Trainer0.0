package com.yusuf.personaltrainer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: MealEntryEntity)

    @Query("SELECT * FROM meal_entries WHERE date = :date")
    fun getEntriesByDate(date: String): Flow<List<MealEntryEntity>>

    @Query("DELETE FROM meal_entries WHERE date = :date")
    suspend fun deleteAllForDate(date: String)
}