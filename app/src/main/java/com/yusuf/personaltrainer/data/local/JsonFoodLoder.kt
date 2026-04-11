package com.yusuf.personaltrainer.data.local

import android.content.Context
import android.util.Log
import com.google.gson.JsonParser
import com.yusuf.personaltrainer.data.local.entity.FoodEntity

object JsonFoodLoader {

    private val files = listOf(
        "fruits.json",
        "protein.json",
        "grains.json",
        "vegetables.json",
        "dairy.json",
        "prepared_food.json",
        "treats.json",
        "fast_food.json"
    )

    fun loadFoodsFromJson(context: Context): List<FoodEntity> {

        val allFoods = mutableListOf<FoodEntity>()

        for (fileName in files) {

            val jsonString = context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }

            val jsonArray = JsonParser.parseString(jsonString).asJsonArray

            for (element in jsonArray) {
                try {
                    val obj = element.asJsonObject

                    val name = obj["name"].asString
                    val category = obj["category"].asString

                    val nutrition = obj["nutrition"].asJsonObject

                    val food = FoodEntity(
                        name = name,
                        category = category,
                        calories = nutrition["calories"].asDouble,
                        protein = nutrition["protein"].asDouble,
                        carbs = nutrition["carbs"].asDouble,
                        fat = nutrition["fat"].asDouble
                    )

                    allFoods.add(food)

                } catch (e: Exception) {
                    Log.d("JSON_DEBUG", "Skipped item in $fileName")
                }
            }
        }

        Log.d("JSON_DEBUG", "Total foods loaded = ${allFoods.size}")

        return allFoods
    }
}