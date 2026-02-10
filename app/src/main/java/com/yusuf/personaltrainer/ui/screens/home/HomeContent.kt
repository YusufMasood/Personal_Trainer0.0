package com.yusuf.personaltrainer.ui.screens.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.personaltrainer.ui.viewModel.UserProfileViewModel

@Composable
fun HomeContent(
    userProfileViewModel: UserProfileViewModel,
    onProfileClick: () -> Unit
) {
    Column(modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

       Spacer(modifier = Modifier.heightIn(18.dp))

        Row(modifier = Modifier
            ) {

            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(56.dp)
                        .clickable(onClick = onProfileClick)
                )
            }

            Spacer(modifier = Modifier.width(40.dp))
            val profile by userProfileViewModel.profile.collectAsState()

            val userName = profile?.name ?: "Guest"

            Text("Welcome, $userName", fontSize = 25.sp)
        }

    }
}
