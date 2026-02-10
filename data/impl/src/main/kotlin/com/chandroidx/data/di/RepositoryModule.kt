package com.chandroidx.data.di

import com.chandroidx.data.repository.MainRepository
import com.chandroidx.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindMainRepository(
    mainRepositoryImpl: MainRepositoryImpl,
  ): MainRepository
}
