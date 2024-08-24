package com.example.dhandha.data.local.rent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "rent_user")
data class RentUserEntity(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val phone: String,
    val address: String,
    val image: String?,
    val isActive: Boolean,
    val agreementStartDate: Long,
    val agreementEndDate: Long,
    val rentStartDate: Long,
    val rentExpiryDate: Long,
    val joinedDate: Long,
    val rentAmount: Int,
    val advanceAmountPaid: Int,
    val securityAmountPaid: Int,
    val pendingAmount: Int,
    val amountPaid: Int
)