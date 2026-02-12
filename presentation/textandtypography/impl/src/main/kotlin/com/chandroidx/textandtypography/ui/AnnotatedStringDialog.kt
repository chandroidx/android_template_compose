package com.chandroidx.textandtypography.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

@Composable
internal fun AnnotatedStringDialog() {
  Text(
    text = buildAnnotatedString {
      append(LoremIpsum().values.first().substring(0, 50))
      withStyle(
        SpanStyle(fontWeight = FontWeight.Bold),
      ) {
        append(LoremIpsum().values.first().substring(50, 100))
      }

      withStyle(
        SpanStyle(fontStyle = FontStyle.Italic),
      ) {
        append(LoremIpsum().values.first().substring(100, 150))
      }
    },
    color = MaterialTheme.colorScheme.primary,
  )
}
