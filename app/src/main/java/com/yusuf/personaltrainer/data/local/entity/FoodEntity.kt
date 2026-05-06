package com.yusuf.personaltrainer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "foods",
    indices = [Index(value = ["name"]), Index(value = ["category"])])
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val category: String,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val servingName: String,
    val gramsPerServing: Double
)
