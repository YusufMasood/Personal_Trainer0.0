package com.yusuf.personaltrainer.ui

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.yusuf.personaltrainer.Ui_Content.AppDrawer
import com.yusuf.personaltrainer.Ui_Content.HomeContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun homeScreen() {



    //App closing handler*******************************


    val context = LocalContext.current
    var backPressedOnce by remember { mutableStateOf(false) }

    // Reset after 2 seconds
    LaunchedEffect(backPressedOnce) {
        if (backPressedOnce) {
            delay(2000)
            backPressedOnce = false
        }
    }

    BackHandler {
        if (backPressedOnce) {
            // Exit app
            (context as? Activity)?.finish()
        } else {
            backPressedOnce = true
            Toast.makeText(
                context,
                "Press back again to exit",
                Toast.LENGTH_SHORT
            ).show()


        }
    }

    //*********************************************************



    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                onItemClick = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        HomeContent(
            onProfileClick = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}


@Preview
@Composable
fun show3(){

    homeScreen()

}