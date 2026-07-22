package com.chandroidx.network.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import javax.inject.Inject

class NetworkLoggerImpl @Inject constructor(
  @param:ApplicationContext private val context: Context,
) : NetworkLogger {
  private val file: File by lazy {
    File(context.getExternalFilesDir("log"), NetworkLogger.LOG_FILE_NAME)
  }

  override fun log(message: String) {
    try {
      FileOutputStream(file, true).use { outputStream ->
        PrintWriter(outputStream).use { writer ->
          writer.appendLine(message)
          writer.appendLine("======================================================================================================")
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}
