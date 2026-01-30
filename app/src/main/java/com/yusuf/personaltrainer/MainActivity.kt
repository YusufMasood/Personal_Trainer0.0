package com.yusuf.personaltrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yusuf.personaltrainer.ui.introScreen
import com.yusuf.personaltrainer.ui.theme.MyApplicationTheme
import androidx.navigation.compose.composable
import com.yusuf.personaltrainer.ui.loginScreen
import com.yusuf.personaltrainer.ui.homeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {




                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "intro"
                ) {
                    composable("intro"){
                        introScreen( onGetStarted = { navController.navigate("login")})
                    }
                    composable("login"){
                        loginScreen(onGoHome = {navController.navigate("home"){ popUpTo("login") { inclusive = true }} })
                    }
                    composable("home"){
                        homeScreen()
                    }
                }

            }
        }
    }
}

