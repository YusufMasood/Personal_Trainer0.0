package com.yusuf.personaltrainer.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NeonProgressCard(
    name: String,
    weight: Int,
    calories: Int,
    weeklyProgress: Float
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF00E5FF),
                spotColor = Color(0xFF00E5FF)
            ),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF12121A)
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CircularProgressIndicator(
                progress = weeklyProgress,
                color = Color(0xFF00E5FF),
                strokeWidth = 8.dp,
                modifier = Modifier.size(120.dp)
            )

            Spacer(Modifier.height(12.dp))

            Text(name, color = Color.White)

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("💪 $weight kg", color = Color.White)
                Text("🔥 $calories kcal", color = Color.White)
            }
        }
    }
}




@Composable
fun SettingsSection() {

    Column {

        Text(
            "Preferences",
            modifier = Modifier.padding(16.dp),
            color = Color.Gray
        )

        SettingItem(Icons.Default.DarkMode, "Dark Mode") {}
        SettingItem(Icons.Default.Notifications, "Notifications") {}
        SettingItem(Icons.Default.Security, "Privacy") {}
        SettingItem(Icons.Default.LocationOn, "Location Access") {}
    }
}




@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    ListItem(
        leadingContent = { Icon(icon, null) },
        headlineContent = { Text(title) },
        modifier = Modifier.clickable { onClick() }
    )
}




@Composable
fun SectionDivider() {
    Divider(
        color = Color(0xFF2A2A2A),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}