package com.chandroidx.network.di

import com.chandroidx.network.util.NetworkLogger
import com.chandroidx.network.util.NetworkLoggerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractNetworkModule {
  @Binds
  abstract fun bindNetworkLogger(
    networkLoggerImpl: NetworkLoggerImpl,
  ): NetworkLogger
}
