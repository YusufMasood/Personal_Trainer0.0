package com.yusuf.personaltrainer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_entries")
data class MealEntryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val date: String,          // yyyy-MM-dd
    val mealType: String,      // Breakfast / Lunch / Snacks / Dinner
    val foodId: Long,

    val quantityInGrams: Double,

    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)