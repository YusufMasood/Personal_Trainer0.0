package com.yusuf.personaltrainer.ui.screens.FoodItems

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.ui.components.AddFoodCard
import com.yusuf.personaltrainer.ui.components.GradientLinearProgressBar


@Composable
fun MealScreen(
    consumedCalories: Int,
    targetCalories: Int,
    protein: Int,
    carbs: Int,
    fat: Int,
){

    val rawProgress = consumedCalories.toFloat() / targetCalories
    val progress = rawProgress.coerceIn(0f, 1f)

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(700),
        label = "CaloriesProgress"
    )
    val percentage = (rawProgress * 100).toInt()

    val gradient = when {
        rawProgress > 1f ->
            listOf(Color.Red, Color(0xFFFF5252))
        rawProgress >= 0.5f ->
            listOf(Color(0xFF4CAF50), Color(0xFF81C784))
        else ->
            listOf(Color(0xFFFFC107), Color(0xFFFFECB3))
    }

    Box(modifier = Modifier.padding(12.dp)){

        Column() {


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ){
                Text("uigwiur")



                GradientLinearProgressBar(
                    progress = animatedProgress,
                    gradientColors = gradient,
                    modifier = Modifier.fillMaxWidth()
                )



            }




            // cards for add food items
            AddFoodCard()

        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun  showFoods(){
    MealScreen(1600,2000,160,256,60)
}