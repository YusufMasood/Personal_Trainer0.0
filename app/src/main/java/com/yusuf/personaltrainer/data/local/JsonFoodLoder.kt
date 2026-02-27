package com.yusuf.personaltrainer.data.local

import android.content.Context
import android.util.Log
import com.google.gson.JsonParser
import com.yusuf.personaltrainer.data.local.entity.FoodEntity

object JsonFoodLoader {

    fun loadFoodsFromJson(context: Context): List<FoodEntity> {

        val inputStream = context.assets.open("foods_final.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        Log.d("JSON_DEBUG", "JSON length = ${jsonString.length}")

        val jsonArray = JsonParser.parseString(jsonString).asJsonArray
        val foods = mutableListOf<FoodEntity>()

        for (element in jsonArray) {

            val obj = element.asJsonObject

            if (!obj.has("foodId") || !obj.has("name")) continue

            try {

                val food = FoodEntity(
                    foodId = obj["foodId"].asLong,
                    name = obj["name"].asString,
                    category = null, // since JSON doesn't have it
                    caloriesPer100g = obj["caloriesPer100g"].asDouble,
                    proteinPer100g = obj["proteinPer100g"].asDouble,
                    carbsPer100g = obj["carbsPer100g"].asDouble,
                    fatPer100g = obj["fatPer100g"].asDouble,
                    fiberPer100g = obj["fiberPer100g"].asDouble
                )

                foods.add(food)

            } catch (e: Exception) {
                Log.d("JSON_DEBUG", "Skipped item due to error")
            }
        }

        Log.d("JSON_DEBUG", "Parsed foods manually = ${foods.size}")

        return foods
    }
}