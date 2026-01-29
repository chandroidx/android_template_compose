package com.chandroidx.template.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.template.R
import com.chandroidx.textandtypography.AnnotatedStringNavKey
import com.chandroidx.textandtypography.TextNavKey

sealed interface TextAndTypography : Component {
  data object Text : TextAndTypography {
    override val navKey: NavKey = TextNavKey
    override val iconResId: Int = R.drawable.text
    override val textResId: Int = R.string.text
    override val descriptionResId: Int? = null
  }

  data object AnnotatedString : TextAndTypography {
    override val navKey: NavKey = AnnotatedStringNavKey
    override val iconResId: Int = R.drawable.annotated_string
    override val textResId: Int = R.string.annotated_string
    override val descriptionResId: Int? = null
  }

  companion object {
    fun all() = listOf(Text, AnnotatedString)
  }
}
