package com.yusuf.personaltrainer.ui.screens.FoodItems


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.data.local.entity.FoodEntity
import com.yusuf.personaltrainer.ui.components.FoodRow


@Composable
fun FoodSelectionScreen(
    mealType: String,
    onFoodAdded: () -> Unit
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }

    var searchQuery by remember { mutableStateOf("") }
    var foods by remember { mutableStateOf<List<FoodEntity>>(emptyList()) }

    LaunchedEffect(searchQuery) {

        foods = if (searchQuery.isBlank()) {
            db.foodDao().getAllFoods()
        } else {
            db.foodDao().searchFoods(searchQuery)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Add Food to $mealType",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search Food") }
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(foods) { food ->

                FoodRow(
                    food = food,
                    mealType = mealType,
                    onFoodAdded = onFoodAdded
                )
            }
        }
    }
}

@Preview
@Composable
fun showFoodList(){
    FoodSelectionScreen("",{})
}

