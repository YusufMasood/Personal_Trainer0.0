package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.IntroScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.composable
import com.example.myapplication.ui.LoginPage
import com.example.myapplication.ui.homeScreen


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
                        IntroScreen( onGetStarted = { navController.navigate("login")})
                    }
                    composable("login"){
                        LoginPage(onGoHome = {navController.navigate("home"){ popUpTo("login") { inclusive = true }} })
                    }
                    composable("home"){
                        homeScreen()
                    }
                }

            }
        }
    }
}

