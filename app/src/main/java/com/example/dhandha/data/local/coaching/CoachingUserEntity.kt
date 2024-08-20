package com.example.dhandha.data.local.coaching

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("coaching_user")
data class CoachingUserEntity(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val phone: String,
    val address: String,
    val isActive: Boolean,
    val subjects: String,
    val fees: Int,
    val pending: Int,
    val monthStartDate: Long,
    val monthExpiryDate: Long,
    val joinedDate: Long
)