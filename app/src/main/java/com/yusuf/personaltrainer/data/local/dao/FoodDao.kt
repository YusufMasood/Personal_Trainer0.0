package com.yusuf.personaltrainer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusuf.personaltrainer.data.local.entity.FoodEntity

@Dao
interface FoodDao {

    @Query("SELECT COUNT(*) FROM foods")
    suspend fun getFoodCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foods: List<FoodEntity>)

    @Query("SELECT * FROM foods")
    suspend fun getAllFoods(): List<FoodEntity>

    @Query("""SELECT * FROM foods WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR LOWER(category) LIKE '%' || LOWER(:query) || '%'
    ORDER BY 
        CASE 
            WHEN name LIKE :query || '%' THEN 1   -- starts with
            WHEN name LIKE '%' || :query || '%' THEN 2
            WHEN category LIKE '%' || :query || '%' THEN 3
            ELSE 4
        END, name ASC LIMIT 50""")
    suspend fun searchFoods(query: String): List<FoodEntity>

    @Query("SELECT * FROM foods WHERE category = :category")
    suspend fun getFoodsByCategory(category: String): List<FoodEntity>

    @Query("""SELECT * FROM foods WHERE name LIKE :query || '%'""")
    suspend fun searchStartsWith(query: String): List<FoodEntity>
}