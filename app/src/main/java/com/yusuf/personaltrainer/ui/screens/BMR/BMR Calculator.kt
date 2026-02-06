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
    var activityLevel by remember { mutableStateOf("Item 1") }

    var gender by remember { mutableStateOf("Male") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F2EF))
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = "Calculate BMR",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                    .background(Color(0xFFBDBDBD))
                    .padding(16.dp)
            ) {

                Text(
                    text = "Enter Your Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

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
                        listOf("Sedentary", "Light", "Moderate", "Active").forEach {
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
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Calculate Button
                Button(
                    onClick = {
                        // TODO: BMR calculation logic
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
                        text = "CALCULATE",
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