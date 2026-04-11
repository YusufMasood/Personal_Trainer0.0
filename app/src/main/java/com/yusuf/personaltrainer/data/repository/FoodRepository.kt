package com.yusuf.personaltrainer.data.repository


import com.yusuf.personaltrainer.data.local.dao.FoodDao
import com.yusuf.personaltrainer.data.local.entity.FoodEntity

class FoodRepository(
    private val foodDao: FoodDao
) {

    suspend fun getAllFoods(): List<FoodEntity> {
        return foodDao.getAllFoods()
    }

    suspend fun searchFoods(query: String): List<FoodEntity> {
        if (query.isBlank()) return emptyList()
        return foodDao.searchFoods(query.trim())
    }
}