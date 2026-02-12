package com.yusuf.personaltrainer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1, // only one user
    val name: String = "Guest",
    val age: Int = 0,
    val weightKg: Float = 0f,
    val heightInch: Int = 0,
    val phoneNumber: String = "0000000000"
)

