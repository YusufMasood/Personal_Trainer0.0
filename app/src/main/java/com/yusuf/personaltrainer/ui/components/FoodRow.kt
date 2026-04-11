package com.yusuf.personaltrainer.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.entity.FoodEntity
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
import com.yusuf.personaltrainer.utils.DateUtils
import kotlinx.coroutines.*

@Composable
fun FoodRow(
    food: FoodEntity,
    mealType: String,
    onFoodAdded: () -> Unit
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }

    var quantity by remember { mutableStateOf("100") }

    // 🔥 Live calculation
    val qty = quantity.toDoubleOrNull() ?: 0.0
    val factor = qty / 100.0

    val liveCalories = (food.calories * factor)
    val liveProtein = (food.protein * factor)
    val liveCarbs = (food.carbs * factor)
    val liveFat = (food.fat * factor)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            // Food Name
            Text(text = food.name)

            Spacer(Modifier.height(6.dp))

            // Quantity Input
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity (g)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            // 🔥 LIVE CALORIES
            Text(
                text = "Calories: ${liveCalories.toInt()} kcal"
            )

            Spacer(Modifier.height(4.dp))

            // 🔥 LIVE MACROS
            Text(
                text = "P: ${liveProtein.toInt()}g | " +
                        "C: ${liveCarbs.toInt()}g | " +
                        "F: ${liveFat.toInt()}g"
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {

                    val safeQty = quantity.toDoubleOrNull()?.takeIf { it > 0 } ?: return@Button
                    val safeFactor = safeQty / 100.0

                    val entry = MealEntryEntity(
                        date = DateUtils.today(),
                        mealType = mealType,
                        foodId = food.id,
                        quantityInGrams = safeQty,
                        calories = food.calories * safeFactor,
                        protein = food.protein * safeFactor,
                        carbs = food.carbs * safeFactor,
                        fat = food.fat * safeFactor
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        db.mealEntryDao().insert(entry)

                        withContext(Dispatchers.Main) {
                            onFoodAdded()
                        }
                    }
                }
            ) {
                Text("Add")
            }
        }
    }
}