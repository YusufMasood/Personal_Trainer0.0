package com.yusuf.personaltrainer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yusuf.personaltrainer.data.local.AppDatabase
import com.yusuf.personaltrainer.ui.screens.auth.PersonalInfoScreen
import com.yusuf.personaltrainer.ui.screens.auth.PersonalInfoViewModel
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
                    navController.navigate(Routes.PERSONAL_INFO) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen()
        }


        //  PERSONAL INFO SCREEN (REAL DB USE)
        composable(Routes.PERSONAL_INFO) {

            val context = LocalContext.current

            val db = remember {
                AppDatabase.getInstance(context)
            }

            val viewModel = remember {
                PersonalInfoViewModel(db.userProfileDao())
            }

            PersonalInfoScreen(
                viewModel = viewModel,
                onContinue = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.PERSONAL_INFO) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}

