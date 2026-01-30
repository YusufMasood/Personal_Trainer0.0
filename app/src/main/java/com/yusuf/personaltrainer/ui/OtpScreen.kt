package com.yusuf.personaltrainer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuf.personaltrainer.R
import kotlinx.coroutines.delay


@Composable
fun otpScreen(){


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



//************************************************************************************


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(35.dp))

        Text("OTP verification", fontSize = 25.sp, fontWeight = FontWeight.Bold)


        Box{
            Image(
                painter = painterResource(R.drawable.otpsent),
                contentDescription = "otp image",
                modifier = Modifier.size(280.dp)
            )
        }


        Spacer(modifier = Modifier.height(15.dp))


        Text("Enter OTP", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        Text("OTP has been sent on *****")


        //OTP fields

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){}
    }

}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun show2(){
    otpScreen()
}