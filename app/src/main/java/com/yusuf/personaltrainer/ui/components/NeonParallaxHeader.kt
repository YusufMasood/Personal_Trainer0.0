package com.yusuf.personaltrainer.ui.components


import com.yusuf.personaltrainer.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp






@Composable
fun NeonParallaxHeader(scrollState: LazyListState,
                       name: String,
                       email: String,
                       onEditClick: () -> Unit

) {


    val offset = scrollState.firstVisibleItemScrollOffset * 0.4f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF00E5FF),
                spotColor = Color(0xFF00E5FF)
            ),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF12121A)
        )
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color(0xFF00E5FF),
                spotColor = Color(0xFF00E5FF)
            )

        ) {
            Image(
                painter = painterResource(R.drawable.profile_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .graphicsLayer {
                        translationY = -offset
                    }
            )


            Column(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
                Text(name, color = Color.White, fontSize = 18.sp)
                Text(email, color = Color.Gray, fontSize = 14.sp)
            }

            OutlinedButton(onClick = onEditClick) {
                Text("Edit")
            }


        }
    }
}

@Preview
@Composable
fun NeonParallaxHeaderShow(){

    val listState = rememberLazyListState()

    NeonParallaxHeader(scrollState = listState,"Yusuf","dfgh98765t",{})
}

