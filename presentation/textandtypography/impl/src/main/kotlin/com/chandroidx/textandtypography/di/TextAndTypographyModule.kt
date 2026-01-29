package com.chandroidx.textandtypography.di

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.textandtypography.AnnotatedStringNavKey
import com.chandroidx.textandtypography.TextNavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object TextAndTypographyModule {
  @IntoSet
  @Provides
  fun provideTextAndTypographyEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<TextNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      Text(
        text = LoremIpsum().values.first().substring(0, 100),
        color = MaterialTheme.colorScheme.primary,
      )
    }

    entry<AnnotatedStringNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
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
  }
}
