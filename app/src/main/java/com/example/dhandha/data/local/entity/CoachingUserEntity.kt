package com.example.dhandha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dhandha.data.ListOfStringConverter
import java.util.UUID

@Entity("coaching_user")
data class CoachingUserEntity(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val phone: String,
    val address: String,
    val image: String?,
    val isActive: Boolean,
    val lastPaymentAmount: Int,

    @TypeConverters(ListOfStringConverter::class)
    val subjects: List<String>,
    val fees: Int,
    val pending: Int,
    val monthStartDate: Long,
    val monthExpiryDate: Long,
    val lastPaymentDate: Long,
    val joinedDate: Long
)