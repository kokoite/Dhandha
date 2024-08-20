package com.example.dhandha.data.local.gym

import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val weight: Int,
    val height: Int,
    val planAmount: Int,
    val pendingAmount: Int,
    val joinedDate: Long,
    val planStartDate: Long,
    val planExpiryDate: Long,
)