package com.example.dhandha.di

import com.example.dhandha.data.repository.CoachingUserRepository
import com.example.dhandha.data.repository.CoachingUserRepositoryImpl
import com.example.dhandha.data.repository.GymUserRepository
import com.example.dhandha.data.repository.GymUserRepositoryImpl
import com.example.dhandha.data.repository.RentUserRepository
import com.example.dhandha.data.repository.RentUserRepositoryImpl
import com.example.dhandha.services.Service
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

    @Binds
    abstract fun providesCoachingUserRepository(repositoryImpl: CoachingUserRepositoryImpl): CoachingUserRepository

    @Binds
    abstract fun providesGymUserRepository(repositoryImpl: GymUserRepositoryImpl): GymUserRepository
}