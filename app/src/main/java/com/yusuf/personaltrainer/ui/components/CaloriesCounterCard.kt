package com.yusuf.personaltrainer.ui.components




import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CaloriesCounterCard(
    consumedCalories: Int,
    targetCalories: Int,
    onFoodAdd: () -> Unit

) {
    val rawProgress = consumedCalories.toFloat() / targetCalories
    val progress = rawProgress.coerceIn(0f, 1f)

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(700),
        label = "CaloriesProgress"
    )

    val gradient = when {
        rawProgress > 1f ->
            listOf(Color.Red, Color(0xFFFF5252))
        rawProgress >= 0.5f ->
            listOf(Color(0xFF4CAF50), Color(0xFF81C784))
        else ->
            listOf(Color(0xFFFFC107), Color(0xFFFFECB3))
    }

    val percentage = (rawProgress * 100).toInt()

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
                "$consumedCalories / $targetCalories kcal",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                "$percentage% of daily goal",
                color = gradient.first()
            )

            Spacer(Modifier.height(8.dp))

            GradientLinearProgressBar(
                progress = animatedProgress,
                gradientColors = gradient,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = onFoodAdd,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add Food")
            }

        }
    }
}





@Composable
fun GradientLinearProgressBar(
    progress: Float,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(
                    brush = Brush.horizontalGradient(gradientColors)
                )
        )
    }
}


@Composable
fun MacroBreakdown(
    protein: Int,
    carbs: Int,
    fat: Int
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = if (expanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        AnimatedVisibility(expanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text("Protein: ${protein}g")
                Text("Carbs: ${carbs}g")
                Text("Fat: ${fat}g")
            }
        }
    }
}





@Preview
@Composable
fun showClaories(){
    CaloriesCounterCard(1145,1500,{})
}
