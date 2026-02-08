package com.yusuf.personaltrainer.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun PersonalInfoScreen(
    viewModel: PersonalInfoViewModel,
    onContinue: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var feet by rememberSaveable { mutableStateOf("") }
    var inch by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(

                focusedBorderColor = Color(0xFF2CD5DB),
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFF2CD5DB),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color(0xFF2CD5DB),

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )


        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(

                focusedBorderColor = Color(0xFF2CD5DB),
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFF2CD5DB),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color(0xFF2CD5DB),

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )


        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (Kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(

                focusedBorderColor = Color(0xFF2CD5DB),
                unfocusedBorderColor = Color.Gray,

                focusedLabelColor = Color(0xFF2CD5DB),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color(0xFF2CD5DB),

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )


        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = feet,
                onValueChange = { feet = it },
                label = { Text("Feet") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color(0xFF2CD5DB),
                    unfocusedBorderColor = Color.Gray,

                    focusedLabelColor = Color(0xFF2CD5DB),
                    unfocusedLabelColor = Color.Gray,

                    cursorColor = Color(0xFF2CD5DB),

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )

            OutlinedTextField(
                value = inch,
                onValueChange = { inch = it },
                label = { Text("Inch") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color(0xFF2CD5DB),
                    unfocusedBorderColor = Color.Gray,

                    focusedLabelColor = Color(0xFF2CD5DB),
                    unfocusedLabelColor = Color.Gray,

                    cursorColor = Color(0xFF2CD5DB),

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        val isFormValid = name.isNotBlank() &&
                age.isNotBlank() &&
                weight.isNotBlank() &&
                feet.isNotBlank() &&
                inch.isNotBlank()



        Button(
            onClick = {
                viewModel.savePersonalInfo(name, age, weight, feet, inch) {
                    onContinue()
                }
            },

            enabled = isFormValid,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .width(200.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContentColor = Color.White.copy(alpha = 0.5f)
            ),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(if (isFormValid) 1f else 0.4f) // 👈 HERE
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF5B86E5), Color(0xFF2CD5DB))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Save", fontWeight = FontWeight.Bold, color = Color.White)
            }

        }

    }
}





