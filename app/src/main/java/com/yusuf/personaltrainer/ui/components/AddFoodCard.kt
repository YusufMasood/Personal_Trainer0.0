package com.yusuf.personaltrainer.ui.components




import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AddFoodCard(){

    Column() {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Breakfast ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = "(Kcal)")

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Alarm,
                    contentDescription = "Add Alarm",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "AddButton",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )
            }
        }



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Lunch ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = "(Kcal)")

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Alarm,
                    contentDescription = "Add Alarm",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "AddButton",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )
            }
        }



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Snacks ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = "(Kcal)")

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Alarm,
                    contentDescription = "Add Alarm",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "AddButton",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )
            }
        }




        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Dinner ",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = "(Kcal)")

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Alarm,
                    contentDescription = "Add Alarm",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "AddButton",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )
            }
        }

    }
}

@Preview
@Composable
fun showFoodCrad(){
    AddFoodCard()
}