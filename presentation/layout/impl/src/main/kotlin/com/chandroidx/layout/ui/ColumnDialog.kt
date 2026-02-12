package com.chandroidx.layout.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
internal fun ColumnDialog() {
  Column(
    modifier = Modifier
      .size(200.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val pagerState = rememberPagerState { 3 }
    HorizontalPager(
      modifier = Modifier
        .padding(5.dp)
        .weight(1f),
      state = pagerState,
    ) { page ->
      when (page) {
        COLUMN -> {
          PageDescriptionWrapper(
            description = "Column",
            modifier = Modifier.fillMaxSize(),
          ) {
            Column(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
              FilledBox(
                modifier = Modifier
                  .fillMaxWidth()
                  .alpha(0.5f)
                  .weight(1f),
              )

              FilledBox(
                modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f),
              )

              FilledBox(
                modifier = Modifier
                  .fillMaxWidth()
                  .alpha(0.5f)
                  .weight(1f),
              )
            }
          }
        }

        LAZY_COLUMN -> {
          PageDescriptionWrapper(
            description = "LazyColumn",
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

            LazyColumn(
              modifier = Modifier.fillMaxSize(),
              userScrollEnabled = false,
              verticalArrangement = Arrangement.spacedBy(10.dp),
              state = state,
            ) {
              items(10) { index ->
                FilledBox(
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
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

        FLOW_COLUMN -> {
          PageDescriptionWrapper(
            description = "FlowColumn",
            modifier = Modifier.fillMaxSize(),
          ) {
            FlowColumn(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.spacedBy(5.dp),
              horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
              repeat(50) {
                FilledBox(
                  modifier = Modifier
                    .height((10..50).random().dp)
                    .width(15.dp),
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

private const val COLUMN = 0
private const val LAZY_COLUMN = 1
private const val FLOW_COLUMN = 2
