package com.example.myapplication.ui


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun LoginPage( onGoHome: () -> Unit){

    var phoneNumber by remember { mutableStateOf("") }

    Column( modifier = Modifier
        .fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally

    ){

        Spacer(modifier = Modifier.height(25.dp))


        Text(
            text = "Welcome", fontWeight = FontWeight.Bold, fontSize = 30.sp,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.Start)
        )



        //Image

        Box{
            Image(painter = painterResource(R.drawable.otpsent),
                contentDescription = "Otp image",
                modifier = Modifier.size(280.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Enter your phone number", fontSize = 20.sp, fontWeight = FontWeight.Bold)


        Spacer(modifier = Modifier.height(8.dp))


        //Enter phone number

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                if (it.length <= 10) phoneNumber = it
            },
            modifier = Modifier.width(300.dp),
            label = { Text("Phone Number") },
            leadingIcon = {
                Text(
                    text = "+91- ",
                    color = Color.Black,   // <-- +91 color
                    modifier = Modifier.padding(start = 8.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Black
            )
        )


        Spacer(modifier = Modifier.height(45.dp))


        //Send otp button


        Box(
            modifier = Modifier
                .width(200.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF5B86E5), Color(0xFF2CD5DB))
                    )
                )
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Verify OTP",
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(85.dp))

        Text("———— OR SIGN IN WITH ————", fontSize =  15.sp)


        Spacer(modifier = Modifier.height(25.dp))

        Row() {
            ElevatedButton( {},
                modifier = Modifier
                    .padding(12.dp)
            ){
                Text("Google")
            }

            ElevatedButton( {},
                modifier = Modifier
                    .padding(12.dp)
            ){
                Text("Facebook")
            }

            ElevatedButton( {},
                modifier = Modifier
                    .padding(12.dp)
            ){
                Text("Twitter")
            }
        }
        Button( onClick = onGoHome) { Text("Home Page") }


    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun show1(){

    LoginPage{}

}