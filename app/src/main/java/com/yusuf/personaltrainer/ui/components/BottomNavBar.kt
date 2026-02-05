package com.yusuf.personaltrainer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .height(72.dp)
                .shadow(
                    elevation = 35.dp,
                    shape = RoundedCornerShape(40.dp),
                    ambientColor = Color.Black.copy(0.85f),
                    spotColor = Color.Black.copy(0.9f)
                )
                .clip(RoundedCornerShape(40.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1C1F26),
                            Color(0xFF0E1116)
                        )
                    )
                ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = selectedIndex == 0
            ) { onItemSelected(0) }

            BottomNavItem(
                icon = Icons.Default.Person,
                label = "Coach",
                isSelected = selectedIndex == 1
            ) { onItemSelected(1) }

            // Scan button (NO TEXT)
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00E5FF))
                    .clickable { onItemSelected(2) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Scan",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }

            BottomNavItem(
                icon = Icons.Default.Construction,
                label = "Tools",
                isSelected = selectedIndex == 3
            ) { onItemSelected(3) }

            BottomNavItem(
                icon = Icons.Default.Settings,
                label = "Settings",
                isSelected = selectedIndex == 4
            ) { onItemSelected(4) }
        }
    }
}




@Composable
fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) Color(0xFF00E5FF) else Color.White

    Column(
        modifier = Modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = color
        )
    }
}

@Preview
@Composable
fun show4(){
    BottomNavBar(2,{})
}