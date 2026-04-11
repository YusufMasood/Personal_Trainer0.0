package com.yusuf.personaltrainer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusuf.personaltrainer.data.local.dao.DailyNutritionDao
import com.yusuf.personaltrainer.data.local.dao.UserProfileDao
import com.yusuf.personaltrainer.data.local.entity.DailyNutritionEntity
import com.yusuf.personaltrainer.data.local.entity.UserProfileEntity
import com.yusuf.personaltrainer.data.local.entity.FoodEntity
import com.yusuf.personaltrainer.data.local.dao.FoodDao
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
import com.yusuf.personaltrainer.data.local.dao.MealEntryDao

@Database(
    entities = [UserProfileEntity::class,
              DailyNutritionEntity::class,
              FoodEntity::class,
        MealEntryEntity::class],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
    abstract fun dailyNutritionDao(): DailyNutritionDao

    abstract fun foodDao(): FoodDao

    abstract fun mealEntryDao(): MealEntryDao



    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personal_trainer_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }

}



