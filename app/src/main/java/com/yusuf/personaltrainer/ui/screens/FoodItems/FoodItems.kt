package com.yusuf.personaltrainer.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FoodItems(){

    Box(){
        Text("We will show our food data here")


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Calories", style = MaterialTheme.typography.titleMedium)

                Spacer(Modifier.height(6.dp))

                Text(
                    " / kcal",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    "$ of daily goal"
                )

                Spacer(Modifier.height(8.dp))



                Spacer(Modifier.height(12.dp))


                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add Food")
                }
            }
        }
    }

}


@Preview
@Composable
fun  showFoods(){
    FoodItems()
}