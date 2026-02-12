package com.chandroidx.layout.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
internal fun FilledBox(
  modifier: Modifier = Modifier,
  content: @Composable (BoxScope.() -> Unit)? = null,
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(3.dp))
      .background(MaterialTheme.colorScheme.primary),
  ) {
    content?.invoke(this)
  }
}
