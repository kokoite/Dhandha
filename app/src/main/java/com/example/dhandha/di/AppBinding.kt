package com.example.dhandha.di

import com.example.dhandha.data.repository.RentUserRepository
import com.example.dhandha.data.repository.RentUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinding {
    @Binds
    abstract fun providesRentUserRepository(repositoryImpl: RentUserRepositoryImpl): RentUserRepository
}