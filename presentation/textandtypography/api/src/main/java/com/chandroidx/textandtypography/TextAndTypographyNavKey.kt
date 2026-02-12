package com.chandroidx.textandtypography

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface TextAndTypographyNavKey : NavKey {
  @Serializable
  data object Text : TextAndTypographyNavKey

  @Serializable
  data object AnnotatedString : TextAndTypographyNavKey
}
