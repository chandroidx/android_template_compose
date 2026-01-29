package com.chandroidx.barcodescanning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chandroidx.core.CameraPreview
import com.chandroidx.core.drawRect
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode

@Composable
fun BarcodeScanning(
  modifier: Modifier = Modifier,
) {
  val detector = remember { BarcodeScanning.getClient() }
  var barcodeScanningResult by remember { mutableStateOf<Barcode?>(null) }

  fun Barcode.stringBarcodeFormat(): String = when (format) {
    Barcode.FORMAT_CODE_128 -> "CODE 128"
    Barcode.FORMAT_CODE_39 -> "CODE 39"
    Barcode.FORMAT_CODE_93 -> "CODE 93"
    Barcode.FORMAT_CODABAR -> "CODABAR"
    Barcode.FORMAT_DATA_MATRIX -> "DATA MATRIX"
    Barcode.FORMAT_EAN_13 -> "EAN 13"
    Barcode.FORMAT_EAN_8 -> "EAN 8"
    Barcode.FORMAT_ITF -> "ITF"
    Barcode.FORMAT_QR_CODE -> "QR CODE"
    Barcode.FORMAT_UPC_A -> "UPC A"
    Barcode.FORMAT_UPC_E -> "UPC E"
    Barcode.FORMAT_PDF417 -> "PDF417"
    Barcode.FORMAT_AZTEC -> "AZTEC"
    else -> "Unknown"
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Box(
      modifier = modifier
        .aspectRatio(1f)
        .fillMaxWidth(),
    ) {
      CameraPreview(
        modifier = Modifier.fillMaxSize(),
        detector = detector,
      ) { result ->
        result.getValue(detector)?.let { barcodes ->
          barcodeScanningResult = barcodes.firstOrNull()
        }
      }

      if (barcodeScanningResult != null) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .drawWithContent {
              drawRect(barcodeScanningResult!!.boundingBox!!)
            },
        )
      }
    }

    if (barcodeScanningResult != null) {
      Spacer(
        modifier = Modifier.height(20.dp),
      )

      Text(
        text = barcodeScanningResult!!.stringBarcodeFormat(),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )

      Spacer(
        modifier = Modifier.height(10.dp),
      )

      Text(
        text = barcodeScanningResult!!.rawValue.toString(),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )
    }
  }
}
