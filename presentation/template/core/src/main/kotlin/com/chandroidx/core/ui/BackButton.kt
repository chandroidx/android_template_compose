package com.chandroidx.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chandroidx.template.core.R

@Composable
fun Header(
  title: String,
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.fillMaxWidth()) {
    BackButton(
      modifier = Modifier.align(Alignment.CenterStart),
      onClick = onBackClick,
    )

    Text(
      modifier = Modifier.align(Alignment.Center),
      text = title,
      color = MaterialTheme.colorScheme.primary,
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Composable
private fun BackButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Image(
    modifier = modifier
      .clip(RoundedCornerShape(3.dp))
      .clickable(onClick = onClick),
    painter = painterResource(R.drawable.ico_back),
    contentDescription = null,
    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
  )
}
