package com.yusuf.personaltrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yusuf.personaltrainer.ui.introScreen
import com.yusuf.personaltrainer.ui.theme.MyApplicationTheme
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yusuf.personaltrainer.ui.loginScreen
import com.yusuf.personaltrainer.ui.homeScreen
import com.yusuf.personaltrainer.ui.otpScreen


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

                    composable("intro") {
                        introScreen(
                            onGetStarted = {
                                navController.navigate("login")
                            }
                        )
                    }

                    composable("login") {
                        loginScreen(
                            onGoHome = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            },
                            sendOtp = { phoneNumber, verId ->
                                navController.navigate("otp/$phoneNumber /$verId")
                            }
                        )
                    }

                    composable(
                        route = "otp/{phone}/{verId}",
                        arguments = listOf(
                            navArgument("phone") { type = NavType.StringType },
                            navArgument("verId") { type = NavType.StringType }
                        )

                    ) { backStackEntry ->

                        val phone = backStackEntry.arguments?.getString("phone") ?: ""
                        val verificationId = backStackEntry.arguments?.getString("verId") ?: ""

                        otpScreen(
                            phoneNumber = phone,
                            verificationId = verificationId,
                            onLoginSuccess = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )

                    }


                    composable("home") {
                        homeScreen()
                    }





                }


            }
        }
    }
}

