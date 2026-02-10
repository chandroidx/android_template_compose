package com.chandroidx.core.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PageDescriptionWrapper(
  description: String,
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit,
) {
  Box(modifier = modifier) {
    Column(
      modifier = Modifier.align(Alignment.Center),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = description,
        color = MaterialTheme.colorScheme.primary,
      )
      content()
    }
  }
}

@Composable
fun PageIndicator(
  pagerState: PagerState,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    repeat(pagerState.pageCount) { page ->
      val width by animateDpAsState(targetValue = if (pagerState.currentPage == page) 20.dp else 10.dp)
      val background by animateColorAsState(targetValue = if (pagerState.currentPage == page) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
      Box(
        modifier = Modifier
          .clip(CircleShape)
          .width(width)
          .height(10.dp)
          .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
          .background(background),
      )
    }
  }
}
