package com.yusuf.personaltrainer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.personaltrainer.R
import kotlinx.coroutines.delay



@Composable
fun otpScreen(phoneNumber : String) {


    //OTP boxes
    val otp = remember { mutableStateListOf("", "", "", "", "", "") }
    val focusRequesters = List(6) { FocusRequester() }


    //OTP timer
    var secondsLeft by remember { mutableIntStateOf(60) }
    var resendEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
        resendEnabled = true
    }


    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }



//************************************************************************************


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(55.dp))

        Text("OTP verification", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)


        Spacer(modifier = Modifier.height(35.dp))

        Box {
            Image(
                painter = painterResource(R.drawable.otpsent),
                contentDescription = "otp image",
                modifier = Modifier.size(280.dp)
            )
        }


        Spacer(modifier = Modifier.height(35.dp))


        Text("Enter OTP", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Text("OTP has been sent on +91- $phoneNumber")


        //OTP fields

        Spacer(modifier = Modifier.height(35.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            otp.forEachIndexed { index, value ->


                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .focusRequester(focusRequesters[index]),
                    contentAlignment = Alignment.Center
                ) {
                    BasicTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                otp[index] = newValue
                                if (newValue.isNotEmpty() && index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }



            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (!resendEnabled) {
            Text(
                text = "Resend OTP in $secondsLeft sec",
                color = Color.Gray
            )
        } else {
            Text(
                text = "Resend OTP",
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }


        //Verify Button


        Spacer(modifier = Modifier.height(50.dp))

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

    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun show2(){
    otpScreen("675678907")
}