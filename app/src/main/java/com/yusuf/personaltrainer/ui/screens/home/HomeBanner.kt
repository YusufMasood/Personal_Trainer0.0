package com.yusuf.personaltrainer.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.yusuf.personaltrainer.R

data class HomeBannerItem(
    val imageRes: Int,
    val title: String
)

val homeBannerItems = listOf(
    HomeBannerItem(R.drawable.salman,"Salman Khan"),
    HomeBannerItem(R.drawable.chris, "Chris Bumstead"),
    HomeBannerItem(R.drawable.kaigreen, "Kai Greene"),
    HomeBannerItem(R.drawable.hadi, "Hadi Choopan"),
    HomeBannerItem(R.drawable.arnold,"Arnold Schwarzenegger"),
    HomeBannerItem(R.drawable.ronnie,"Roni Coleman"),
    HomeBannerItem(R.drawable.bigrammy,"Big Ramy")
)


@Composable
fun HomeBannerSlider(
    items: List<HomeBannerItem>,
    autoScrollDelay: Long = 5000L
) {
    if (items.isEmpty()) return

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp



    val infiniteCount = Int.MAX_VALUE
    val startIndex = infiniteCount / 2 - (infiniteCount / 2) % items.size

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = startIndex
    )

    var currentPage by remember { mutableStateOf(0) }

    // Track current logical page
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                currentPage = index % items.size
            }
    }

    // Auto-scroll (still infinite)
    LaunchedEffect(currentPage) {
        delay(autoScrollDelay)
        if (!listState.isScrollInProgress) {
            listState.animateScrollToItem(
                listState.firstVisibleItemIndex + 1
            )
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(80.dp))

        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(infiniteCount) { index ->
                val realIndex = index % items.size

                HomeBannerItemView(
                    item = items[realIndex],
                    width = screenWidth,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        PageIndicator(
            totalDots = items.size,
            selectedIndex = currentPage
        )
    }
}



@Composable
fun HomeBannerItemView(
    item: HomeBannerItem,
    width: Dp,
) {
    Box(
        modifier = Modifier
            .width(width)
            .aspectRatio(16f / 9f)
            .padding(horizontal = 24.dp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false
            )
    ) {

        // 👇 ACTUAL CARD CONTENT
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.DarkGray)
        ) {

            Image(
                painter = painterResource(item.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.65f)
                            )
                        )
                    )
            )

            Text(
                text = item.title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            )
        }
    }
}





@Composable
fun PageIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(
                        if (index == selectedIndex) 10.dp else 8.dp
                    )
                    .clip(CircleShape)
                    .background(
                        if (index == selectedIndex)
                            Color.Black
                        else
                            Color.LightGray
                    )
            )
        }
    }
}
