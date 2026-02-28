package com.yusuf.personaltrainer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.personaltrainer.data.local.entity.MealEntryEntity


@Composable
fun MealSection(
    mealType: String,
    items: List<MealEntryEntity>,
    onAddClick: () -> Unit
) {

    val totalCalories = items.sumOf { it.calories }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "$mealType (${totalCalories.toInt()} Kcal)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "Add Food",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onAddClick() }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            items.forEach { entry ->

                Text(
                    text = "${entry.quantityInGrams}g - ${entry.calories.toInt()} Kcal",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}