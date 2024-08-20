package com.example.dhandha.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import com.example.dhandha.data.local.db.DhandhaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Provides
    @Singleton
    fun providesDb(@ApplicationContext application: Context): DhandhaDatabase {
        return Room.databaseBuilder(application, DhandhaDatabase::class.java, "dhandha_db").build()
    }
}