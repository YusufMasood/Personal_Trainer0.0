package com.yusuf.personaltrainer.ui.screens.FoodItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuf.personaltrainer.ui.components.AddFoodCard


@Composable
fun CaloriesScreen(){

    Box(modifier = Modifier.padding(12.dp)){


        // cards for add food items
        AddFoodCard()
    }

}


@Preview(showSystemUi = true)
@Composable
fun  showFoods(){
    CaloriesScreen()
}