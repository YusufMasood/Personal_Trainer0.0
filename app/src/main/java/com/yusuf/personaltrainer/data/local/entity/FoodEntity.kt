package com.yusuf.personaltrainer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "foods",
    indices = [Index(value = ["name"])]
)
data class FoodEntity(
    @PrimaryKey
    val foodId: Long,

    val name: String,
    val category: String?,

    val caloriesPer100g: Double,
    val proteinPer100g: Double,
    val carbsPer100g: Double,
    val fatPer100g: Double,
    val fiberPer100g: Double
)
