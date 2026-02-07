package com.yusuf.personaltrainer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.yusuf.personaltrainer.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: UserProfileEntity)

    @Query("UPDATE user_profile SET phoneNumber = :phone WHERE id = 1")
    suspend fun updatePhoneNumber(phone: String)

    @Query("""UPDATE user_profile SET 
        name = :name,
        age = :age,
        weightKg = :weight,
        heightInch = :height
    WHERE id = 1""")
    suspend fun updatePersonalInfo(
        name: String,
        age: Int,
        weight: Float,
        height: Int
    )


    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getProfile(): Flow<UserProfileEntity?>
}