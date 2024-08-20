package com.example.dhandha.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dhandha.data.local.coaching.CoachingUserDao
import com.example.dhandha.data.local.coaching.CoachingUserEntity
import com.example.dhandha.data.local.gym.GymUserDao
import com.example.dhandha.data.local.gym.GymUserEntity
import com.example.dhandha.data.local.library.LibraryUserDao
import com.example.dhandha.data.local.library.LibraryUserEntity
import com.example.dhandha.data.local.owner.OwnerDao
import com.example.dhandha.data.local.owner.OwnerEntity
import com.example.dhandha.data.local.rent.RentUserDao
import com.example.dhandha.data.local.rent.RentUserEntity

@Database(entities = [OwnerEntity::class, CoachingUserEntity::class, RentUserEntity::class, GymUserEntity::class, LibraryUserEntity::class], version = 1, exportSchema = false)

abstract class DhandhaDatabase: RoomDatabase() {
    abstract fun ownerDao(): OwnerDao
    abstract fun coachingUserDao(): CoachingUserDao
    abstract fun rentUserDao(): RentUserDao
    abstract fun gymUserDao(): GymUserDao
    abstract fun libraryDao(): LibraryUserDao
}