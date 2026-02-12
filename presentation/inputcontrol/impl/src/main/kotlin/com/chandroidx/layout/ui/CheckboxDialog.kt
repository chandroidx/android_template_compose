package com.chandroidx.layout.ui

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
internal fun CheckboxDialog() {
  var checked by remember { mutableStateOf(false) }
  Checkbox(
    checked = checked,
    onCheckedChange = {
      checked = it
    },
    colors = CheckboxDefaults.colors(
      checkedColor = MaterialTheme.colorScheme.primary,
      uncheckedColor = MaterialTheme.colorScheme.secondary,
      checkmarkColor = MaterialTheme.colorScheme.background,
    ),
  )
}
