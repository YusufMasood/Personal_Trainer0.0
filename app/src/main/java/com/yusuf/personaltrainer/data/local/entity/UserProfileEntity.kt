package com.yusuf.personaltrainer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1, // only one user
    val name: String,
    val age: Int,
    val weightKg: Float,
    val heightInch: Int,
    val phoneNumber: String
)