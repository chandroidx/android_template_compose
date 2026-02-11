package com.chandroidx.template.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.template.R
import com.deepfine.naver.NaverNavKey

sealed interface Api : Component {
  data object Naver : Api {
    override val navKey: NavKey = NaverNavKey
    override val iconResId: Int = R.drawable.ico_naver
    override val textResId: Int = R.string.naver
    override val descriptionResId: Int? = null

    @Composable
    override fun iconTint(): Color? = null
  }

  companion object {
    fun all() = listOf<Api>(Naver)
  }
}
