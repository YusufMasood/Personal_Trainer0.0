package com.example.myapplication.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginPage( onGoHome: () -> Unit){

    Box( modifier = Modifier
        .fillMaxSize()

    ){

        Text("We will work omhngfdsdagn this.")

        Button( onClick = onGoHome, modifier = Modifier.align(Alignment.BottomCenter)) { Text("Home Page") }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun show1(){

}