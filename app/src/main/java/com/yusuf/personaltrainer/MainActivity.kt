package com.yusuf.personaltrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.yusuf.personaltrainer.navigation.AppNavGraph
import com.yusuf.personaltrainer.ui.theme.MyApplicationTheme
import com.yusuf.personaltrainer.ui.viewModel.UserProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val userProfileViewModel: UserProfileViewModel = viewModel()
            val accentColor by userProfileViewModel.selectedColor.collectAsState()

            MyApplicationTheme(
                darkTheme = true,
                accentColor = accentColor
            ) {

                val navController = rememberNavController()

                // 🔥 THIS WAS MISSING
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    AppNavGraph(navController)
                }
            }
        }
    }
}
