package com.chandroidx.mlkit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chandroidx.mlkit.MlKitNavKey
import com.github.skydoves.navgraph.annotations.NavDestination
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

@Composable
@NavDestination(route = MlKitNavKey.ImageLabeling::class)
internal fun ImageLabelingDialog(
  modifier: Modifier = Modifier,
) {
  val labeler = remember { ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS) }
  val imageLabelResult = remember { mutableStateListOf<ImageLabel>() }

  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp),
  ) {
    CameraPreview(
      modifier = Modifier
        .aspectRatio(1f)
        .fillMaxWidth(),
      detector = labeler,
    ) { result ->
      result.getValue(labeler)?.let { imageLabels ->
        imageLabelResult.clear()
        imageLabelResult.addAll(imageLabels)
      }
    }

    if (imageLabelResult.isNotEmpty()) {
      Text(
        text = imageLabelResult
          .maxBy { imageLabel ->
            imageLabel.confidence
          }.text,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )
    }
  }
}
