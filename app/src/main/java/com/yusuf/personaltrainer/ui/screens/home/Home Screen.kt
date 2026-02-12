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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yusuf.personaltrainer.ui.components.*
import com.yusuf.personaltrainer.ui.screens.Tools.ToolsScreen
import com.yusuf.personaltrainer.ui.viewModel.UserProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {

    val userProfileViewModel: UserProfileViewModel = viewModel()

    val homeNavController = rememberNavController()
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

            // 🔥 NAV HOST OUTSIDE LAZYCOLUMN
            NavHost(
                navController = homeNavController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize()
            ) {

                composable("home") {

                    // Only Home screen scrollable content
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

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
                                100,
                                78,
                                34,
                                {}
                            )
                        }
                    }
                }

                composable("coach") {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Coach screen content
                    }
                }

                composable("scan") {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Scan screen content
                    }
                }

                composable("tools") {
                    ToolsScreen()
                }

                composable("settings") {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Settings screen content
                    }
                }
            }
        }

        // 🟨 Bottom Nav Floating
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomNavBar(
                selectedIndex = currentIndex(homeNavController),
                onItemSelected = { index ->
                    when (index) {
                        0 -> homeNavController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                        1 -> homeNavController.navigate("coach")
                        2 -> homeNavController.navigate("scan")
                        3 -> homeNavController.navigate("tools")
                        4 -> homeNavController.navigate("settings")
                    }
                }
            )
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

