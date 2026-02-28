package com.yusuf.personaltrainer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CaloriesExpandableCard(
    consumedCalories: Int,
    targetCalories: Int,
    protein: Int,
    proteinTarget: Int,
    carbs: Int,
    carbsTarget: Int,
    fat: Int,
    fatTarget: Int
) {

    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(400),
        label = "ArrowRotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // 🔥 Calories Section
            Text(
                text = "Target Calories",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            Spacer(Modifier.height(6.dp))

            val (calorieText, calorieExceeded) =
                calculateRemainingOrExceed(
                    consumedCalories.toDouble(),
                    targetCalories.toDouble()
                )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "$consumedCalories of $targetCalories Kcal",
                    color = Color.Black
                )

                Text(
                    text = calorieText + " Kcal",
                    color = if (calorieExceeded) Color.Red else Color.Gray
                )
            }

            val calorieProgress =
                if (targetCalories > 0)
                    (consumedCalories.toFloat() / targetCalories)
                        .coerceIn(0f, 1f)
                else 0f

            Spacer(Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = calorieProgress,
                color = if (calorieExceeded) Color.Red else Color(0xFF3B82F6),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(6.dp))
            )

            Spacer(Modifier.height(16.dp))

            // 🔽 Smooth Expand
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {

                Column {

                    MacroRow(
                        title = "Protein",
                        value = protein.toDouble(),
                        target = proteinTarget.toDouble(),
                        progressColor = Color(0xFFEF4444)
                    )

                    Spacer(Modifier.height(16.dp))

                    MacroRow(
                        title = "Carbs",
                        value = carbs.toDouble(),
                        target = carbsTarget.toDouble(),
                        progressColor = Color(0xFF10B981)
                    )

                    Spacer(Modifier.height(16.dp))

                    MacroRow(
                        title = "Fats",
                        value = fat.toDouble(),
                        target = fatTarget.toDouble(),
                        progressColor = Color(0xFFF59E0B)
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // 🔥 Bottom Center Arrow Button
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Surface(
                    shape = CircleShape,
                    color = Color(0xFF1F2937),
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { expanded = !expanded }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.rotate(rotation)
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun MacroRow(
    title: String,
    value: Double,
    target: Double,
    progressColor: Color
) {

    val progress = (value / target).coerceIn(0.0, 1.0)

    val (statusText, exceeded) = calculateRemainingOrExceed(value, target)

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(title, color = Color.Black)
                Text("${value.format(1)} of ${target.format(1)} g", color = Color.Black)
            }

            Text(
                text = statusText + " g",
                color = if (exceeded) Color.Red else Color.Gray
            )
        }

        Spacer(Modifier.height(6.dp))

        LinearProgressIndicator(
            progress = progress.toFloat(),
            color = if (exceeded) Color.Red else progressColor,
            trackColor = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}


fun calculateRemainingOrExceed(value: Double, target: Double): Pair<String, Boolean> {
    return if (value <= target) {
        "Remaining : ${(target - value).format(1)}" to false
    } else {
        "Exceeds By : ${(value - target).format(1)}" to true
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)





@Preview
@Composable
fun showClaories2(){
    CaloriesExpandableCard(1800,
        2000,
        189,
        200,
        256,
        200,
        60,
        60)
}
