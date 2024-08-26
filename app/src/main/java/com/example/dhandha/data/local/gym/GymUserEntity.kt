package com.example.dhandha.data.local.gym

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dhandha.data.ListOfStringConverter
import java.util.UUID

@Entity("gym_user")
data class GymUserEntity (
    @PrimaryKey
    val id: UUID,
    val name: String,
    val phone: String,
    val address: String,
    val existingDisease: String,
    val isActive: Boolean,
    val weight: Float,
    val height: Float,
    val planAmount: Int,
    val pendingAmount: Int,
    val joinedDate: Long,
    val lastPaymentAmount: Int,
    val lastPaymentDate: Long,
    val planStartDate: Long,
    val planExpiryDate: Long,
    val image: String?,
    @TypeConverters(ListOfStringConverter:: class)
    val progress: List<String>
)