package com.chandroidx.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.chandroidx.core.Component
import com.chandroidx.template.core.R
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ComponentColumn(
  title: String?,
  components: ImmutableList<Component>,
  onComponentClick: (Component) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    if (title != null) {
      Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )
    }

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(size = 10.dp))
        .border(width = 1.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(size = 10.dp)),
    ) {
      components.forEachIndexed { index, item ->
        ComponentItem(item, onClick = { onComponentClick(item) })

        if (index < components.lastIndex) {
          HorizontalDivider(
            modifier = Modifier.padding(horizontal = 1.dp),
            color = MaterialTheme.colorScheme.secondary,
          )
        }
      }
    }
  }
}

@Composable
private fun ComponentItem(
  component: Component,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val isComponentImplemented = component.navKey != null

  Row(
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 70.dp)
      .clickable(enabled = isComponentImplemented, onClick = onClick)
      .alpha(if (isComponentImplemented) 1f else 0.5f)
      .padding(horizontal = 15.dp, vertical = 15.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(15.dp),
  ) {
    Image(
      modifier = Modifier.size(24.dp),
      painter = painterResource(component.iconResId),
      colorFilter = component.iconTint()?.let(ColorFilter::tint),
      contentDescription = null,
    )

    Column(
      modifier = Modifier.weight(1f),
    ) {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(component.textResId),
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = if (isComponentImplemented) null else TextDecoration.LineThrough,
      )

      if (component.descriptionResId != null) {
        Text(
          modifier = Modifier
            .fillMaxWidth(),
          text = stringResource(component.descriptionResId!!),
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.secondary,
          style = TextStyle(
            lineHeight = 1.em,
          ),
        )
      }
    }

    Icon(
      modifier = Modifier.size(15.dp),
      painter = painterResource(R.drawable.arrow_right),
      contentDescription = null,
      tint = MaterialTheme.colorScheme.secondary,
    )
  }
}
