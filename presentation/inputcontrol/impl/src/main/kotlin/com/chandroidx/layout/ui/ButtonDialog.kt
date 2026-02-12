package com.chandroidx.layout.ui

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun ButtonDialog() {
  val context = LocalContext.current

  Button(
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.primary,
    ),
    onClick = {
      Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
    },
  ) {
  }
}
