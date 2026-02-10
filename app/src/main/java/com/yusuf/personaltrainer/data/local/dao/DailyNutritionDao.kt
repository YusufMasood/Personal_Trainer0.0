package com.yusuf.personaltrainer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusuf.personaltrainer.data.local.entity.DailyNutritionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyNutritionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: DailyNutritionEntity)

    @Query("""
        UPDATE daily_nutrition 
        SET calories = 0, protein = 0, carbs = 0, fat = 0 
        WHERE date = :date
    """)
    suspend fun resetToday(date: String)

    @Query("SELECT * FROM daily_nutrition WHERE date = :date")
    suspend fun getByDate(date: String): DailyNutritionEntity?

    @Query("SELECT * FROM daily_nutrition WHERE date = :date")
    fun getByDateFlow(date: String): Flow<DailyNutritionEntity?>


}