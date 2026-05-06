package com.yusuf.personaltrainer.ui.screens.BMR


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmrScreen() {

    var weight by remember { mutableStateOf("") }
    var heightFeet by remember { mutableStateOf("") }
    var heightInch by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var activityLevel by remember { mutableStateOf("Moderate") }

    var gender by remember { mutableStateOf("Male") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        // Title
        Text(
            text = "Calculate BMR",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Card Container
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .background(Color(0xFF808080))
                    .padding(16.dp)
            ) {

                Text(
                    text = "Enter Your Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(11.dp))

                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight in kg") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = heightFeet,
                    onValueChange = { heightFeet = it },
                    label = { Text("Height in feet") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = heightInch,
                    onValueChange = { heightInch = it },
                    label = { Text("Height in inch") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = activityLevel,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Activity Level") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf("Sedentary", "Light", "Moderate", "Active!").forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    activityLevel = it
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Gender",
                    fontWeight = FontWeight.Medium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = gender == "Male",
                        onClick = { gender = "Male" }
                    )
                    Text("Male")

                    Spacer(modifier = Modifier.width(24.dp))

                    RadioButton(
                        selected = gender == "Female",
                        onClick = { gender = "Female" }
                    )
                    Text("Female")


                    Spacer(modifier = Modifier.width(24.dp))

                    RadioButton(
                        selected = gender == "Mentally sick",
                        onClick = { gender = "Mentally sick"}
                    )
                    Text("Mentally sick")

                }

                Spacer(modifier = Modifier.height(80.dp))

                // Calculate Button
                Button(
                    onClick = {

                        val heightCm = ((heightFeet.toDoubleOrNull() ?: 0.0) * 30.48) +
                                ((heightInch.toDoubleOrNull() ?: 0.0) * 2.54)

                        val weightKg = weight.toDoubleOrNull() ?: 0.0
                        val ageVal = age.toIntOrNull() ?: 0

                        val bmr = when (gender) {
                            "Male" -> (10 * weightKg) + (6.25 * heightCm) - (5 * ageVal) + 5
                            "Female" -> (10 * weightKg) + (6.25 * heightCm) - (5 * ageVal) - 161
                            else -> 0.0
                        }

                        val activityMultiplier = when (activityLevel) {
                            "Sedentary" -> 1.2
                            "Light" -> 1.375
                            "Moderate" -> 1.55
                            "Active!" -> 1.725
                            else -> 1.2
                        }

                        val tdee = bmr * activityMultiplier

                        println("BMR: $bmr")
                        println("TDEE (Calories): $tdee")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8E63F4)
                    )
                ) {
                    Text(
                        text = "CALCULATE 😬",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}


@Preview
@Composable
fun showBmr(){
    BmrScreen()
}