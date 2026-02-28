package com.yusuf.personaltrainer.ui.screens.FoodItems

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.ui.components.AddFoodCard
import com.yusuf.personaltrainer.ui.components.CaloriesExpandableCard
import com.yusuf.personaltrainer.ui.components.GradientLinearProgressBar
import com.yusuf.personaltrainer.ui.components.MealSection
import com.yusuf.personaltrainer.utils.DateUtils


@Composable
fun MealScreen(
    onAddFood: (String) -> Unit
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }

    // Observe today's meal entries
    val entries by db.mealEntryDao()
        .getEntriesByDate(DateUtils.today())
        .collectAsState(initial = emptyList())

    // Group by meal type
    val breakfast = entries.filter { it.mealType == "Breakfast" }
    val lunch = entries.filter { it.mealType == "Lunch" }
    val snacks = entries.filter { it.mealType == "Snacks" }
    val dinner = entries.filter { it.mealType == "Dinner" }

    // Calculate daily total
    val dailyCalories = entries.sumOf { it.calories }.toInt()

    Box(modifier = Modifier.padding(12.dp)) {

        LazyColumn {

            // 🔥 Top Expandable Card
            item {
                CaloriesExpandableCard(
                    consumedCalories = dailyCalories,
                    targetCalories = 2000,
                    protein = entries.sumOf { it.protein }.toInt(),
                    proteinTarget = 170,
                    carbs = entries.sumOf { it.carbs }.toInt(),
                    carbsTarget = 256,
                    fat = entries.sumOf { it.fat }.toInt(),
                    fatTarget = 60
                )
            }

            // 🔥 Meal Sections

            item {
                MealSection(
                    mealType = "Breakfast",
                    items = breakfast
                ) {
                    onAddFood("Breakfast")
                }
            }

            item {
                MealSection(
                    mealType = "Lunch",
                    items = lunch
                ) {
                    onAddFood("Lunch")
                }
            }

            item {
                MealSection(
                    mealType = "Snacks",
                    items = snacks
                ) {
                    onAddFood("Snacks")
                }
            }

            item {
                MealSection(
                    mealType = "Dinner",
                    items = dinner
                ) {
                    onAddFood("Dinner")
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun  showFoods(){
    MealScreen({})
}