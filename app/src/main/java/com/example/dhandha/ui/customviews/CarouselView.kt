package com.example.dhandha.ui.customviews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel() {
    val state = rememberPagerState(pageCount = { 4 })
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .background(Color.Yellow), state = state) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.happy_face), contentDescription = "", contentScale = ContentScale.FillBounds, modifier = Modifier
                    .height(240.dp)
                    .width(240.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    ).background(Color.Red))
            }
        }
        CarouselIndicator(state = state)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselIndicator(state: PagerState) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        val normalColor = Color.Gray
        val selectedColor = Color.Red

        repeat(state.pageCount) {

            Box(modifier = Modifier
                .height(6.dp)
                .width(6.dp)
                .clip(CircleShape)
                .background( if(it == state.currentPage) {selectedColor
                } else {
                    normalColor
                }))
        }
    }

}