package com.yusuf.personaltrainer.Ui_Content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.personaltrainer.R

@Composable
fun AppDrawer(
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color.White)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFFE85D5D))
                .padding(16.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Cyan, CircleShape)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("User_name", color = Color.Black, fontWeight = FontWeight.Bold)
                Text("Email_id", color = Color.Black)
            }
        }

        DrawerItem("Profile", Icons.Default.Person, onItemClick)
        DrawerItem("Workout", R.drawable.gym_icon, onItemClick)
        DrawerItem("Pro Tips", R.drawable.tips, onItemClick)
        DrawerItem("Nutritional Value", R.drawable.nutrion, onItemClick)
        DrawerItem("Settings", Icons.Default.Settings, onItemClick)
        DrawerItem("Help & Support", R.drawable.help, onItemClick)
        DrawerItem("Logout", R.drawable.logout, onItemClick)

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "App Version 1.0.0",
            modifier = Modifier.padding(16.dp),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}


@Composable
fun DrawerItem(
    title: String,
    icon: Any,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        when (icon) {
            is ImageVector -> {
                Icon(
                    imageVector = icon,
                    contentDescription = title
                )
            }
            is Int -> {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = title, fontSize = 16.sp)
    }
}

