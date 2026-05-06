package com.yusuf.personaltrainer.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yusuf.personaltrainer.navigation.Routes
import com.yusuf.personaltrainer.ui.components.*
import com.yusuf.personaltrainer.ui.viewModel.UserProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    onFoodAdd : () -> Unit) {

    val userProfileViewModel: UserProfileViewModel = viewModel()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer(
                    userProfileViewModel = userProfileViewModel,
                    onItemClick = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {



            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)) {

                item {
                    HomeContent(
                        userProfileViewModel = userProfileViewModel,
                        onProfileClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }

                item {
                    HomeBannerSlider(items = homeBannerItems)
                }

                item {
                    Spacer(modifier = Modifier.height(42.dp))
                }

                item {
                    CaloriesCounterCard(
                        1700,
                        2000,
                        onFoodAdd
                    )
                }
        }

            // 🟨 Bottom Nav Floating
            Box(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                BottomNavBar(
                    selectedIndex = currentIndex(navController),
                    onItemSelected = { index ->
                        when (index) {
                            0 -> navController.navigate(Routes.HOME) {
                                popUpTo("home") { inclusive = true }
                            }
                            1 -> navController.navigate("coach")
                            2 -> navController.navigate(Routes.ScannerCamera)
                            3 -> navController.navigate(Routes.ToolScreen)
                            4 -> navController.navigate(Routes.SettingScreen)
                        }
                    }
                )
            }
        }



    }
}


@Composable
fun currentIndex(navController: NavHostController): Int {
    val route = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    return when (route) {
        "home" -> 0
        "coach" -> 1
        "scan" -> 2
        "tools" -> 3
        "settings" -> 4
        else -> 0
    }
}

@Preview
@Composable
fun homeShow(){
//    HomeScreen({},{},{})
}

