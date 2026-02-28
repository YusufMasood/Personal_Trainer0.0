package com.yusuf.personaltrainer.ui.screens.FoodItems

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.ui.components.AddFoodCard
import com.yusuf.personaltrainer.ui.components.CaloriesExpandableCard
import com.yusuf.personaltrainer.ui.components.GradientLinearProgressBar


@Composable
fun MealScreen( onAddFood: () -> Unit){

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        val db = AppDatabase.getInstance(context)
        val foods = db.foodDao().getAllFoods()

        Log.d("FOOD_TEST", "Food count = ${foods.size}")
    }

    Box(modifier = Modifier.padding(12.dp)){

        LazyColumn(){

            //Calories Expandible Card

            item {
                CaloriesExpandableCard(
                    1200,
                    2000,
                    160,
                    170,
                    256,
                    200,
                    60,
                    60
                )
            }

            item {

                // cards for add food items
                AddFoodCard(onAddFood)

            }

        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun  showFoods(){
    MealScreen({})
}