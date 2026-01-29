package com.chandroidx.network.datasource

import com.chandroidx.network.service.ApiService
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
  private val service: ApiService,
) : NetworkDataSource
