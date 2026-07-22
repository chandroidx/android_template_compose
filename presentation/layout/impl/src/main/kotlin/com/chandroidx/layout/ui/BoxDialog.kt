package com.chandroidx.layout.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.chandroidx.layout.LayoutNavKey
import com.github.skydoves.navgraph.annotations.NavDestination

@Composable
@NavDestination(route = LayoutNavKey.Box::class)
internal fun BoxDialog() {
  Box(
    modifier = Modifier
      .size(200.dp)
      .padding(5.dp),
  ) {
    FilledBox(
      modifier = Modifier
        .size(80.dp)
        .align(Alignment.TopStart)
        .alpha(0.5f),
    )

    FilledBox(
      modifier = Modifier
        .size(80.dp)
        .align(Alignment.BottomEnd)
        .alpha(0.5f),
    )

    FilledBox(
      modifier = Modifier
        .size(100.dp)
        .align(Alignment.Center),
    )
  }
}
