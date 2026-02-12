package com.chandroidx.layout.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.chandroidx.core.ui.PageDescriptionWrapper
import com.chandroidx.core.ui.PageIndicator
import kotlinx.coroutines.launch

@Composable
internal fun RowDialog() {
  Column(
    modifier = Modifier
      .size(200.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    val pagerState = rememberPagerState { 3 }
    HorizontalPager(
      modifier = Modifier
        .padding(5.dp)
        .weight(1f),
      state = pagerState,
    ) { page ->
      when (page) {
        ROW -> {
          PageDescriptionWrapper(
            modifier = Modifier.fillMaxSize(),
            description = "Row",
          ) {
            Row(
              modifier = Modifier.fillMaxSize(),
              horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
              FilledBox(
                modifier = Modifier
                  .fillMaxHeight()
                  .alpha(0.5f)
                  .weight(1f),
              )

              FilledBox(
                modifier = Modifier
                  .fillMaxHeight()
                  .weight(1f),
              )

              FilledBox(
                modifier = Modifier
                  .fillMaxHeight()
                  .alpha(0.5f)
                  .weight(1f),
              )
            }
          }
        }

        LAZY_ROW -> {
          PageDescriptionWrapper(
            description = "LazyRow",
            modifier = Modifier.fillMaxSize(),
          ) {
            val state = rememberLazyListState()

            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
              coroutineScope.launch {
                while (true) {
                  state.animateScrollBy(10000f, tween(durationMillis = 3000))
                  state.animateScrollBy(-10000f, tween(durationMillis = 3000))
                }
              }
            }

            LazyRow(
              modifier = Modifier.fillMaxSize(),
              userScrollEnabled = false,
              horizontalArrangement = Arrangement.spacedBy(10.dp),
              state = state,
            ) {
              items(10) { index ->
                FilledBox(
                  modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp),
                ) {
                  Text(
                    text = index.toString(),
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.align(Alignment.Center),
                  )
                }
              }
            }
          }
        }

        FLOW_ROW -> {
          PageDescriptionWrapper(
            description = "FlowRow",
            modifier = Modifier.fillMaxSize(),
          ) {
            FlowRow(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.spacedBy(5.dp),
              horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
              repeat(50) {
                FilledBox(
                  modifier = Modifier
                    .width((10..50).random().dp)
                    .height(15.dp),
                )
              }
            }
          }
        }
      }
    }

    PageIndicator(pagerState = pagerState)
  }
}

private const val ROW = 0
private const val LAZY_ROW = 1
private const val FLOW_ROW = 2
