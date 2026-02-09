package com.yusuf.personaltrainer.ui.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yusuf.personaltrainer.ui.components.AppDrawer
import com.yusuf.personaltrainer.ui.components.BottomNavBar
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

        // 🟦 DRAWER
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

            // 🟩 SCROLLABLE CONTENT
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                // 🔝 Home top bar + rest
                item {
                    HomeContent(
                        userProfileViewModel = userProfileViewModel,
                        onProfileClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }

                // 🔥 Banner
                item {
                    HomeBannerSlider(items = homeBannerItems)
                }


                // 🧭 Navigation content
                item {
                    NavHost(
                        navController = homeNavController,
                        startDestination = "home"
                    ) {

                        composable("home") { }

                        composable("coach") { }

                        composable("scan") { }

                        composable("tools") {
                            ToolsScreen()
                        }

                        composable("settings") { }
                    }
                }
            }
        }

        // 🟨 BOTTOM NAV (FLOATING)
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomNavBar(
                selectedIndex = currentIndex(homeNavController),
                onItemSelected = { index ->
                    when (index) {
                        0 -> homeNavController.navigate("home")
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
fun currentIndex(navController: androidx.navigation.NavHostController): Int {
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
fun Show3(){

    HomeScreen()

}