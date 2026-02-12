package com.chandroidx.layout.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
internal fun RadioButtonDialog() {
  val radioStates = remember { mutableStateListOf(true, false, false) }
  Row(horizontalArrangement = Arrangement.SpaceBetween) {
    radioStates.forEachIndexed { index, selected ->
      RadioButton(
        selected = selected,
        onClick = {
          radioStates.replaceAll {
            false
          }

          radioStates[index] = true
        },
        colors = RadioButtonDefaults.colors(
          selectedColor = MaterialTheme.colorScheme.primary,
          unselectedColor = MaterialTheme.colorScheme.secondary,
        ),
      )
    }
  }
}
