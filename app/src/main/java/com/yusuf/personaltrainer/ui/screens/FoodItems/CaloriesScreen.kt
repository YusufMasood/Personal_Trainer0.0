package com.yusuf.personaltrainer.ui.screens.FoodItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.ui.components.AddFoodCard
import com.yusuf.personaltrainer.ui.components.CaloriesCounterCard


@Composable
fun CaloriesScreen(){

    Box(modifier = Modifier.padding(12.dp)){

        Column() {

            //Caloties Progress
            CaloriesCounterCard(
                1600,
                2000,
                170,
                256,
                60,
                {}
            )


            // cards for add food items
            AddFoodCard()

        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun  showFoods(){
    CaloriesScreen()
}