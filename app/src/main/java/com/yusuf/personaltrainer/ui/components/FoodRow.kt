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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.entity.FoodEntity
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
import com.yusuf.personaltrainer.utils.DateUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun FoodRow(
    food: FoodEntity,
    mealType: String,
    onFoodAdded: () -> Unit
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }

    var quantity by remember { mutableStateOf("100") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Text(food.name)

            Spacer(Modifier.height(6.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity (g)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            Button(
                onClick = {

                    val qty = quantity.toDoubleOrNull() ?: 0.0
                    val factor = qty / 100.0

                    val entry = MealEntryEntity(
                        date = DateUtils.today(),
                        mealType = mealType,
                        foodId = food.foodId,
                        quantityInGrams = qty,
                        calories = food.caloriesPer100g * factor,
                        protein = food.proteinPer100g * factor,
                        carbs = food.carbsPer100g * factor,
                        fat = food.fatPer100g * factor
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