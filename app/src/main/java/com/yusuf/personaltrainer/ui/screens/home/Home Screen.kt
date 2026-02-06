package com.yusuf.personaltrainer.ui.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yusuf.personaltrainer.ui.components.AppDrawer
import com.yusuf.personaltrainer.ui.components.BottomNavBar
import com.yusuf.personaltrainer.ui.screens.Tools.ToolsScreen
import kotlinx.coroutines.launch
@Composable
fun HomeScreen() {

    val homeNavController = rememberNavController()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {

        // 🟦 DRAWER WRAPS THE CONTENT
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer(
                    onItemClick = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {

            // 🟩 HOME NAVIGATION
            NavHost(
                navController = homeNavController,
                startDestination = "home"
            ) {

                composable("home") {
                    HomeContent(
                        onProfileClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }

                composable("coach") {
                  //  CoachScreen()
                }

                composable("scan") {
                  //  ScanScreen()
                }

                composable("tools") {
                    ToolsScreen()
                }

                composable("settings") {
                   // SettingsScreen()
                }
            }
        }

        // 🟨 BOTTOM NAV (FLOATING ABOVE EVERYTHING)
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
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
fun show3(){

    HomeScreen()

}