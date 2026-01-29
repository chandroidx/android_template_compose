package com.chandroidx.template.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component

data object TemplateState

sealed interface TemplateIntent {
  data class OnComponentClicked(val component: Component) : TemplateIntent
}

sealed interface TemplateSideEffect {
  data class NavigateTo(val navKey: NavKey) : TemplateSideEffect
}
