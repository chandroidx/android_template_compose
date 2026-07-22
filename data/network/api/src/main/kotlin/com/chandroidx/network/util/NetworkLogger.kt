package com.chandroidx.network.util

import io.ktor.client.plugins.logging.Logger

interface NetworkLogger : Logger {
  companion object {
    const val LOG_FILE_NAME = "network.log"
  }
}
