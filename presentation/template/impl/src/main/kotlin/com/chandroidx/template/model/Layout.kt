package com.chandroidx.template.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.layout.BoxNavKey
import com.chandroidx.layout.ColumnNavKey
import com.chandroidx.layout.RowNavKey
import com.chandroidx.template.R

sealed interface Layout : Component {
  data object Box : Layout {
    override val navKey: NavKey = BoxNavKey
    override val iconResId: Int = R.drawable.box
    override val textResId: Int = R.string.box
    override val descriptionResId: Int? = null
  }

  data object Column : Layout {
    override val navKey: NavKey = ColumnNavKey
    override val iconResId: Int = R.drawable.column
    override val textResId: Int = R.string.column
    override val descriptionResId: Int? = R.string.column_description
  }

  data object Row : Layout {
    override val navKey: NavKey = RowNavKey
    override val iconResId: Int = R.drawable.row
    override val textResId: Int = R.string.row
    override val descriptionResId: Int? = R.string.row_description
  }

  companion object {
    fun all() = listOf(Box, Column, Row)
  }
}
