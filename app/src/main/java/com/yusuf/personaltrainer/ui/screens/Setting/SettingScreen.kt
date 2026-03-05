package com.yusuf.personaltrainer.ui.screens.Setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yusuf.personaltrainer.ui.components.*
import com.yusuf.personaltrainer.ui.viewModel.UserProfileViewModel

@Composable
fun SettingScreen(
    viewModel: UserProfileViewModel = viewModel()
) {

    val listState = rememberLazyListState()

    val name by viewModel.name.collectAsState()
    val weight by viewModel.weight.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val progress by viewModel.weeklyProgress.collectAsState()
    val selectedColor by viewModel.selectedColor.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


       //  Main Content
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = 25.dp)
        ){

            item {

                NeonParallaxHeader(scrollState = listState,
                    name = name,
                    onEditClick = {})
            }

            item {
                NeonColorPicker(
                    selectedColor = selectedColor,
                    onColorSelected = {
                        viewModel.updateColor(it)
                    }
                )
            }

            item { SectionDivider() }

            item {
                NeonProgressCard(
                    name = name,
                    weight = weight,
                    calories = calories,
                    weeklyProgress = progress
                )
            }

            item { SectionDivider() }

            item { SettingsSection() }

            item { SectionDivider() }

            item {
                NeonLogoutButton {
                    viewModel.logout()
                }
            }
        }
    }

}

@Preview
@Composable
fun SettingScreenShow(){
    SettingScreen()
}