package com.chandroidx.template.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.mlkit.MlKitApi
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.template.R

sealed interface MlKit : Component {
  data object Vision : MlKit {
    override val navKey: NavKey = MlKitNavKey(MlKitApi.Vision)
    override val iconResId: Int = R.drawable.ico_vision
    override val textResId: Int = R.string.vision_api
    override val descriptionResId: Int = R.string.vision_api_description
  }

  data object NaturalLanguage : MlKit {
    override val navKey: NavKey = MlKitNavKey(MlKitApi.NaturalLanguage)
    override val iconResId: Int = R.drawable.ico_natural_language
    override val textResId: Int = R.string.natural_language_api
    override val descriptionResId: Int = R.string.natural_language_api_description
  }

  companion object {
    fun all() = listOf(Vision, NaturalLanguage)
  }
}
