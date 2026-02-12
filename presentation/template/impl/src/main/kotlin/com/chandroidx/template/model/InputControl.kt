package com.chandroidx.template.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.inputcontrol.InputControlNavKey
import com.chandroidx.template.R

sealed interface InputControl : Component {
  data object Button : InputControl {
    override val navKey: NavKey = InputControlNavKey.Button
    override val iconResId: Int = R.drawable.button
    override val textResId: Int = R.string.button
    override val descriptionResId: Int? = null
  }

  data object CheckBox : InputControl {
    override val navKey: NavKey = InputControlNavKey.Checkbox
    override val iconResId: Int = R.drawable.check_box
    override val textResId: Int = R.string.check_box
    override val descriptionResId: Int? = null
  }

  data object RadioButton : InputControl {
    override val navKey: NavKey = InputControlNavKey.RadioButton
    override val iconResId: Int = R.drawable.radio_button
    override val textResId: Int = R.string.radio_button
    override val descriptionResId: Int? = null
  }

  data object Switch : InputControl {
    override val navKey: NavKey = InputControlNavKey.Switch
    override val iconResId: Int = R.drawable.input_control_switch
    override val textResId: Int = R.string.input_control_switch
    override val descriptionResId: Int? = null
  }

  data object TextField : InputControl {
    override val navKey: NavKey = InputControlNavKey.TextField
    override val iconResId: Int = R.drawable.text_field
    override val textResId: Int = R.string.text_field
    override val descriptionResId: Int? = R.string.text_field_description
  }

  companion object {
    fun all() = listOf(Button, CheckBox, RadioButton, Switch, TextField)
  }
}
