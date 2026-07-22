package com.chandroidx.textandtypography.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.chandroidx.textandtypography.TextAndTypographyNavKey
import com.github.skydoves.navgraph.annotations.NavDestination

@Composable
@NavDestination(route = TextAndTypographyNavKey.Text::class)
internal fun TextDialog() {
  Text(
    text = LoremIpsum().values.first().substring(0, 100),
    color = MaterialTheme.colorScheme.primary,
  )
}
