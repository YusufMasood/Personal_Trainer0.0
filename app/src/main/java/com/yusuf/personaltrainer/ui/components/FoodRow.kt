package com.yusuf.personaltrainer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.entity.FoodEntity
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity
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

    var quantity by remember { mutableStateOf("1") }

    //  Serving extract (important)
    val servingName = food.servingName
    val gramsPerServing = food.gramsPerServing

    //  Detect unit
    val unitType = remember(servingName) {
        when {
            servingName.lowercase().contains("g") -> "GRAM"
            servingName.lowercase().contains("cup") -> "CUP"
            servingName.lowercase().contains("tbsp") ||
                    servingName.lowercase().contains("spoon") -> "SPOON"
            else -> "PIECE"
        }
    }

    //  Quantity value
    val qtyValue = quantity.toDoubleOrNull() ?: 0.0

    //  FACTOR (CORE LOGIC)
    val factor = when (unitType) {
        "GRAM" -> qtyValue / 100.0
        else -> (qtyValue * gramsPerServing) / 100.0
    }

    val liveCalories = (food.calories * factor)
    val liveProtein = (food.protein * factor)
    val liveCarbs = (food.carbs * factor)
    val liveFat = (food.fat * factor)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            // 🔥 TOP ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = food.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "${liveCalories.toInt()} kcal",
                    color = Color(0xFF00E5FF),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(6.dp))

            // 🔥 Serving info (NEW 🔥)
            Text(
                text = "1 $servingName • ${gramsPerServing.toInt()}g",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(10.dp))

            // 🔥 DYNAMIC INPUT UI
            when (unitType) {

                "GRAM" -> {
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        label = { Text("Quantity (g)") },
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                else -> {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Button(onClick = {
                            val current = quantity.toIntOrNull() ?: 1
                            if (current > 1) quantity = (current - 1).toString()
                        }) {
                            Text("-")
                        }

                        Text(
                            text = "${quantity.toIntOrNull() ?: 1} $servingName",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Button(onClick = {
                            val current = quantity.toIntOrNull() ?: 1
                            quantity = (current + 1).toString()
                        }) {
                            Text("+")
                        }
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            //  MACROS
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("P: ${liveProtein.toInt()}g", color = Color.Green)
                Text("C: ${liveCarbs.toInt()}g", color = Color.Yellow)
                Text("F: ${liveFat.toInt()}g", color = Color.Red)
            }

            Spacer(Modifier.height(14.dp))

            //  ADD BUTTON
            Button(
                onClick = {

                    val safeQty = quantity.toDoubleOrNull()?.takeIf { it > 0 } ?: return@Button

                    //  Convert everything into grams (IMPORTANT)
                    val finalGrams = when (unitType) {
                        "GRAM" -> safeQty
                        else -> safeQty * gramsPerServing
                    }

                    val finalFactor = finalGrams / 100.0

                    val entry = MealEntryEntity(
                        date = System.currentTimeMillis().toString(),
                        mealType = mealType,
                        foodId = food.id,
                        quantityInGrams = finalGrams,
                        calories = food.calories * finalFactor,
                        protein = food.protein * finalFactor,
                        carbs = food.carbs * finalFactor,
                        fat = food.fat * finalFactor
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        db.mealEntryDao().insert(entry)

                        withContext(Dispatchers.Main) {
                            onFoodAdded()
                        }
                    }
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00E5FF)
                )
            ) {
                Text("Add Food", fontWeight = FontWeight.Bold)
            }
        }
    }
}