package com.example.greedygamequoteappassignment.Presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.greedygamequoteappassignment.data.BannerModel
import kotlinx.coroutines.delay

@Composable
fun BannerSlider(bannerList: List<BannerModel> = emptyList()) {

    val pageState = rememberPagerState(initialPage = 0, pageCount = { bannerList.size })

     //Creating issue , Not properly sliding each page
    // It was creating problem , Not sliding fully images
//    LaunchedEffect(key1 = pageState.currentPage) {
//        delay(3000) // 3 seconds
//        val nextPage = (pageState.currentPage + 1) % bannerList.size
//        pageState.animateScrollToPage(nextPage)
//    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pageState.currentPage + 1) % bannerList.size
            pageState.animateScrollToPage(nextPage)
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        HorizontalPager(
            state = pageState,
            pageSize = PageSize.Fill,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .height(250.dp),
            ) {
                AsyncImage(
                    model = bannerList[page].url,
                    contentDescription = "Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            repeat(pageState.pageCount) { index ->
                val color = if (pageState.currentPage == index) {
                    Color.Gray
                } else {
                    Color.Gray.copy(alpha = 0.5f)
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(12.dp)
                        .background(color, shape = CircleShape)
                ) {}
            }
        }
    }
}