package com.yusuf.personaltrainer.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NeonLogoutButton(onLogout: () -> Unit) {

    Button(
        onClick = onLogout,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1F1F2E)
        ),
        border = BorderStroke(2.dp, Color(0xFFFF1744)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            "LOG OUT",
            color = Color(0xFFFF1744),
            fontWeight = FontWeight.Bold
        )
    }
}