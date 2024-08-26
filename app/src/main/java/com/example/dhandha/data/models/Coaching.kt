package com.example.dhandha.data.models

import com.example.dhandha.data.local.coaching.CoachingUserEntity
import com.example.dhandha.helper.convertStringDateToLong
import java.util.UUID

data class CoachingUserListCell(val id: UUID, val name: String, val phone: String,
                                val pendingAmount: String, val feesAmount: String,
                                val expiryDate: String, val image: String?)

data class CoachingUser (val id: UUID, val name: String, val phone: String,
                         val pendingAmount: String, val feesAmount: String,
                         val startDate: String, val expiryDate: String,
                         val joinedDate: String, val isActive: Boolean, val address: String,
                         val lastPaymentDate: String, val lastPaymentAmount: String,
                         val image: String?, val subjects: List<String>) {

    fun toEntity(): CoachingUserEntity {
        return try {
            val joinedDate = convertStringDateToLong(joinedDate) ?: 0
            val pendingAmount = pendingAmount.toInt()
            val lastPaymentDate = convertStringDateToLong(lastPaymentDate) ?: 0
            val lastPaymentAmount = lastPaymentAmount.toInt()
            val expiryDate = convertStringDateToLong(expiryDate) ?: 0
            val startDate = convertStringDateToLong(startDate) ?: 0

            val fees = feesAmount.toInt()
             CoachingUserEntity(id, name, phone,
                joinedDate = joinedDate, isActive = isActive,
                address = address, pending =  pendingAmount, lastPaymentDate = lastPaymentDate,
                 lastPaymentAmount = lastPaymentAmount, fees =  fees,
                 monthExpiryDate = expiryDate, monthStartDate = startDate, subjects = subjects, image = image)
        } catch (e: Error) {
            throw  e
        }
    }
}

