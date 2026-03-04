package com.yusuf.personaltrainer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NeonColorPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {

    val neonColors = listOf(
        Color(0xFF00E5FF),
        Color(0xFFFF1744),
        Color(0xFF76FF03),
        Color(0xFFFFEA00)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        neonColors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(color, CircleShape)
                    .border(
                        width = if (selectedColor == color) 3.dp else 0.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(color) }
            )
        }
    }
}