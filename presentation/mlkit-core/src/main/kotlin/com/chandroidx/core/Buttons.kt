package com.chandroidx.core

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chandroidx.presentation.theme.ApplicationTheme

@Composable
fun TextButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
) {
  Box(
    modifier = modifier
      .wrapContentHeight()
      .border(1.dp, MaterialTheme.colorScheme.primary)
      .clickable(enabled = enabled, onClick = onClick)
      .padding(10.dp),
  ) {
    Text(
      modifier = Modifier.align(Alignment.Center),
      text = text,
      color = MaterialTheme.colorScheme.primary,
    )
  }
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
private fun TextButtonPreview() {
  ApplicationTheme {
    TextButton(
      text = "버튼",
      onClick = {},
    )
  }
}
