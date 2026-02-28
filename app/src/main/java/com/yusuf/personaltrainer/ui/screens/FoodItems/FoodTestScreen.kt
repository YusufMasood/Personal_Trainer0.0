package com.yusuf.personaltrainer.ui.screens.FoodItems


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.repository.FoodRepository

@Composable
fun FoodTestScreen() {

    val context = LocalContext.current

    val db = remember {
        AppDatabase.getInstance(context)
    }

    val repository = remember {
        FoodRepository(db.foodDao())
    }

    val viewModel = remember {
        FoodViewModel(repository)
    }

    val foods by viewModel.foods.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFoods()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Food count: ${foods.size}",
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(foods) { food ->
                Text(
                    text = food.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun showFoodList(){
    FoodTestScreen()
}

