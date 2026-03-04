package com.yusuf.personaltrainer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yusuf.personaltrainer.ui.screens.BMR.BmrScreen
import com.yusuf.personaltrainer.ui.screens.FoodItems.FoodSelectionScreen
import com.yusuf.personaltrainer.ui.screens.FoodItems.MealScreen
import com.yusuf.personaltrainer.ui.screens.Tools.ToolsScreen
import com.yusuf.personaltrainer.ui.screens.auth.PersonalInfoScreen
import com.yusuf.personaltrainer.ui.screens.auth.PersonalInfoViewModel
import com.yusuf.personaltrainer.ui.screens.auth.loginScreen
import com.yusuf.personaltrainer.ui.screens.auth.otpScreen
import com.yusuf.personaltrainer.ui.screens.intro.introScreen
import com.yusuf.personaltrainer.ui.screens.home.HomeScreen

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



        //  PERSONAL INFO SCREEN (REAL DB USE)
        composable(Routes.PERSONAL_INFO) {

            val viewModel: PersonalInfoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()


            PersonalInfoScreen(
                viewModel = viewModel,
                onContinue = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.PERSONAL_INFO) { inclusive = true }
                    }
                }
            )
        }




        //CaloriesAdd screen

        composable(Routes.HOME){
           HomeScreen(
               navController = navController,
               onFoodAdd = {
                   navController.navigate(Routes.Meal)
               },
               onTools = {navController.navigate(Routes.ToolScreen){
                   launchSingleTop = true
                   restoreState = true
                   popUpTo(navController.graph.startDestinationId) {
                       saveState = true
                   }
               } }
           )
        }



        composable(Routes.Meal) {
            MealScreen(
                onAddFood = {mealType ->
                    navController.navigate(
                        Routes.foodSelectionRoute(mealType)
                    )
                }
            )
        }



        // Calling food selection screen
        composable(
            route = Routes.FOOD_SELECTION,
            arguments = listOf(
                navArgument("mealType") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val mealType =
                backStackEntry.arguments?.getString("mealType") ?: "Breakfast"

            FoodSelectionScreen(
                mealType = mealType,
                onFoodAdded = {
                    navController.popBackStack()
                }
            )
        }


        composable(Routes.ToolScreen){
            ToolsScreen(
                onBmi = {navController.navigate(Routes.BMI)}
            )
        }

        composable(Routes.BMI){
            BmrScreen()
        }
    }
}

