package com.yusuf.personaltrainer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yusuf.personaltrainer.ui.screens.auth.loginScreen
import com.yusuf.personaltrainer.ui.screens.auth.otpScreen
import com.yusuf.personaltrainer.ui.screens.home.HomeScreen
import com.yusuf.personaltrainer.ui.screens.intro.introScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.INTRO
    ) {

        composable(Routes.INTRO) {
            introScreen(
                onGetStarted = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.LOGIN) {
            loginScreen(
                onGoHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                sendOtp = { phoneNumber, verId ->
                    navController.navigate(
                        Routes.otpRoute(phoneNumber, verId)
                    )
                }
            )
        }

        composable(
            route = Routes.OTP,
            arguments = listOf(
                navArgument("phone") { type = NavType.StringType },
                navArgument("verId") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val phone = backStackEntry.arguments?.getString("phone").orEmpty()
            val verificationId = backStackEntry.arguments?.getString("verId").orEmpty()

            otpScreen(
                phoneNumber = phone,
                verificationId = verificationId,
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}
