package com.chandroidx.layout.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
internal fun SwitchDialog() {
  var checked by remember { mutableStateOf(false) }

  Switch(
    checked = checked,
    onCheckedChange = {
      checked = it
    },
    colors = SwitchDefaults.colors(
      checkedThumbColor = MaterialTheme.colorScheme.primary,
      checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
      uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
      uncheckedTrackColor = MaterialTheme.colorScheme.background,
    ),
  )
}
