package com.chandroidx.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavKey
import com.chandroidx.presentation.theme.ApplicationTheme

@Stable
interface Component {
  val navKey: NavKey?

  @get:DrawableRes
  val iconResId: Int

  @get:StringRes
  val textResId: Int

  @get:StringRes
  val descriptionResId: Int?

  @Composable
  fun iconTint(): Color? = MaterialTheme.colorScheme.primary
}

@Composable
fun ComponentDialog(
  onDismissRequest: () -> Unit,
  content: @Composable () -> Unit,
) {
  ApplicationTheme {
    Dialog(
      onDismissRequest = onDismissRequest,
      properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
      Box(modifier = Modifier.padding(20.dp)) {
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .wrapContentSize(),
        ) {
          content()
        }
      }
    }
  }
}
