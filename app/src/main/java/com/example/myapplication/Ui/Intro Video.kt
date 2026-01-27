package com.example.myapplication.Ui

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.Ui_Content.VideoBackground

@Composable
fun IntroVideo(){

    Box( modifier = Modifier.fillMaxSize()
    ) {

        VideoBackground()

        //Black gredient overlay

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
        )


        //Elevated button for next page

        ElevatedButton( onClick = {},
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(16.dp)
        ){
            Text("Let's  Get Started")
        }




    }
}



@Preview
@Composable
fun show(){
    IntroVideo()
}