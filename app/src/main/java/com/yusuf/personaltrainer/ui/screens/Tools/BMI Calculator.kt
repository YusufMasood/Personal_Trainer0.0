package com.yusuf.personaltrainer.ui.screens.Tools


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BMI {


    @OptIn(ExperimentalMaterial3Api::class)

    @Composable
    fun bmi(){

        var weight by remember { mutableStateOf("") }
        var HeightFeet by remember { mutableStateOf("") }
        var HeightInch by remember {mutableStateOf("")}
        var Age by remember {mutableStateOf("")}
        val context = LocalContext.current

        val genderOption = listOf("Male", "Female")
        var genderSelected by remember { mutableStateOf(genderOption[0]) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Spacer(modifier = Modifier.height(80.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = {weight = it},

                label = {Text("Weight in KG")},
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Green,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLeadingIconColor = Color.Cyan,
                    unfocusedTrailingIconColor = Color.Cyan
                )


            )


            OutlinedTextField(
                value = HeightFeet,
                onValueChange = {HeightFeet = it},

                label = {Text("Height in Feet")},
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Green,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLeadingIconColor = Color.Cyan,
                    unfocusedTrailingIconColor = Color.Cyan
                )


            )

            OutlinedTextField(
                value = HeightInch,
                onValueChange = {HeightInch = it},

                label = {Text("Height in Inch")},
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Green,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLeadingIconColor = Color.Cyan,
                    unfocusedTrailingIconColor = Color.Cyan
                )


            )


            OutlinedTextField(
                value = Age,
                onValueChange = {Age = it},

                label = {Text("Age")},
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Green,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLeadingIconColor = Color.Cyan,
                    unfocusedTrailingIconColor = Color.Cyan
                )


            )


            Column(modifier = Modifier
                .align(Alignment.Start)
                .padding(22.dp)
                , horizontalAlignment = Alignment.Start) {
                genderOption.forEach { genderOption ->
                    Row( verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected =genderSelected == genderOption,
                            onClick = { genderSelected = genderOption}
                        )
                        Text(text = genderOption)
                    }
                }
            }


            val feet = HeightFeet.toIntOrNull() ?: 0
            val inch = HeightInch.toIntOrNull() ?: 0
            val weight = weight.toIntOrNull() ?: 0

            var totalHight = (feet * 12) + inch
            val hightInMeter = (39.37 * totalHight)

            val BMI = (weight/(hightInMeter * hightInMeter))

            Button(onClick = { Toast.makeText(context,"Your BMI IS :- $BMI ",Toast.LENGTH_SHORT).show()}) {Text("BMI") }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun look(){

    BMI().bmi()

}