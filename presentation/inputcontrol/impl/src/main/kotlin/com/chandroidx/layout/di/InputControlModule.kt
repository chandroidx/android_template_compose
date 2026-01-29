package com.chandroidx.layout.di

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.core.ui.PageDescriptionWrapper
import com.chandroidx.core.ui.PageIndicator
import com.chandroidx.inputcontrol.ButtonNavKey
import com.chandroidx.inputcontrol.CheckBoxNavKey
import com.chandroidx.inputcontrol.RadioButtonNavKey
import com.chandroidx.inputcontrol.SwitchNavKey
import com.chandroidx.inputcontrol.TextFieldNavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object InputControlModule {
  @IntoSet
  @Provides
  fun provideInputControlEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<ButtonNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
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

    entry<CheckBoxNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
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

    entry<RadioButtonNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
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

    entry<SwitchNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
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

    entry<TextFieldNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      Column(
        modifier = Modifier
          .size(200.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        val pagerState = rememberPagerState { 2 }
        HorizontalPager(
          modifier = Modifier
            .padding(5.dp)
            .weight(1f),
          state = pagerState,
        ) { page ->
          when (page) {
            TEXT_FIELD -> PageDescriptionWrapper(
              description = "TextField",
              modifier = Modifier.fillMaxSize(),
            ) {
              val state = rememberTextFieldState()
              TextField(
                state = state,
                colors = TextFieldDefaults.colors(
                  focusedTextColor = MaterialTheme.colorScheme.primary,
                  unfocusedTextColor = MaterialTheme.colorScheme.primary,
                  focusedContainerColor = MaterialTheme.colorScheme.background,
                  unfocusedContainerColor = MaterialTheme.colorScheme.background,
                ),
                textStyle = TextStyle(
                  color = MaterialTheme.colorScheme.primary,
                ),
              )
            }

            OUTLINED_TEXT_FIELD -> PageDescriptionWrapper(
              description = "OutlinedTextField",
              modifier = Modifier.fillMaxSize(),
            ) {
              val state = rememberTextFieldState()

              OutlinedTextField(
                state = state,
                colors = TextFieldDefaults.colors(
                  focusedTextColor = MaterialTheme.colorScheme.primary,
                  unfocusedTextColor = MaterialTheme.colorScheme.primary,
                  focusedContainerColor = MaterialTheme.colorScheme.background,
                  unfocusedContainerColor = MaterialTheme.colorScheme.background,
                ),
                textStyle = TextStyle(
                  color = MaterialTheme.colorScheme.primary,
                ),
              )
            }
          }
        }

        PageIndicator(pagerState = pagerState)
      }
    }
  }

  private const val TEXT_FIELD = 0
  private const val OUTLINED_TEXT_FIELD = 1
}
