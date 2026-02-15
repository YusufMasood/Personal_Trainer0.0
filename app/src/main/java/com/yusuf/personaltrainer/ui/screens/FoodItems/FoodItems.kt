package com.yusuf.personaltrainer.ui.screens.FoodItems

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yusuf.personaltrainer.ui.components.AddFoodCard


@Composable
fun FoodItems(){

    Box(){

        AddFoodCard()
    }

}


@Preview(showSystemUi = true)
@Composable
fun  showFoods(){
    FoodItems()
}