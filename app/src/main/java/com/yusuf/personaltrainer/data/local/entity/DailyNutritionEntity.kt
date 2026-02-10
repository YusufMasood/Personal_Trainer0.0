package com.yusuf.personaltrainer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_nutrition")
data class DailyNutritionEntity(
    @PrimaryKey val date: String, // yyyy-MM-dd
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int
)