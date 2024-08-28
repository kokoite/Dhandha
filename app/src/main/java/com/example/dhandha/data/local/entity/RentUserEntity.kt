package com.example.dhandha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dhandha.data.models.RentUser
import com.example.dhandha.helper.convertLongDateToString
import com.example.dhandha.helper.convertStringDateToLong
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
    val lastPaidAmount: Int,
    val lastPaymentDate: Long
) {
    fun toUser(): RentUser {
        val expiry = convertLongDateToString(rentExpiryDate)
        val startDate = convertLongDateToString(rentStartDate)
        val joined = convertLongDateToString(joinedDate)
        val lastDate = convertLongDateToString(lastPaymentDate)
        val agreementStartDate = convertLongDateToString(agreementStartDate)
        val agreementEndDate = convertLongDateToString(agreementEndDate)

        return RentUser(
            lastPaymentDate = lastDate,
            name = name,
            rent = rentAmount.toString(),
            joinedDate = joined,
            phone = phone,
            address = address,
            image = image,
            rentStart = startDate,
            rentEnd = expiry,
            id = id,
            pending = pendingAmount.toString(),
            paidAmount = lastPaidAmount.toString(),
            security = securityAmountPaid.toString(),
            agreementStart = agreementStartDate,
            agreementEnd = agreementEndDate,
            advance = advanceAmountPaid.toString()
        )
    }
}