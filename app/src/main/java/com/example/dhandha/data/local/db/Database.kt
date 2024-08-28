package com.example.dhandha.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dhandha.data.ListOfStringConverter
import com.example.dhandha.data.local.dao.CoachingUserDao
import com.example.dhandha.data.local.entity.CoachingUserEntity
import com.example.dhandha.data.local.dao.GymUserDao
import com.example.dhandha.data.local.entity.GymUserEntity
import com.example.dhandha.data.local.dao.LibraryUserDao
import com.example.dhandha.data.local.entity.LibraryUserEntity
import com.example.dhandha.data.local.owner.OwnerDao
import com.example.dhandha.data.local.owner.OwnerEntity
import com.example.dhandha.data.local.dao.RentUserDao
import com.example.dhandha.data.local.entity.RentUserEntity

@Database(entities = [OwnerEntity::class, CoachingUserEntity::class, RentUserEntity::class, GymUserEntity::class, LibraryUserEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListOfStringConverter::class)
abstract class DhandhaDatabase: RoomDatabase() {
    abstract fun ownerDao(): OwnerDao
    abstract fun coachingUserDao(): CoachingUserDao
    abstract fun rentUserDao(): RentUserDao
    abstract fun gymUserDao(): GymUserDao
    abstract fun libraryDao(): LibraryUserDao
}