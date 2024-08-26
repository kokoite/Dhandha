package com.example.dhandha.data.models

import com.example.dhandha.data.local.gym.GymUserEntity
import com.example.dhandha.helper.convertStringDateToLong
import java.util.UUID

data class GymUserListCell(val id: UUID, val name: String, val phone: String,
                           val pendingAmount: String, val plantAmount: String,
                           val expiryDate: String, val image: String?)

data class GymUser (val id: UUID, val name: String,
                    val phone: String,
                    val address: String,
                    val isActive: Boolean,
                    val existingDisease: String,
                    val height: String, val weight: String,
                    val pendingAmount: String, val feesAmount: String,
                    val startDate: String, val expiryDate: String,
                    val joinedDate: String,
                    val lastPaymentDate: String,
                    val lastPaymentAmount: String, val image: String?, val progress: List<String>) {

    fun toEntity(): GymUserEntity {
        try {
            val joined = convertStringDateToLong(joinedDate) ?: 0
            val startDate = convertStringDateToLong(startDate) ?: 0
            val expiryDate = convertStringDateToLong(expiryDate) ?: 0
            val lastPaymentDate = convertStringDateToLong(lastPaymentDate) ?: 0
            return GymUserEntity(
                id, pendingAmount = pendingAmount.toInt(), phone = phone, name = name,
                isActive = isActive, address = address, joinedDate = joined,
                weight = weight.toFloat(),
                existingDisease = existingDisease,
                height = height.toFloat(),
                planAmount = feesAmount.toInt(),
                planExpiryDate = expiryDate,
                planStartDate = startDate,
                image = image,
                progress = progress,
                lastPaymentAmount = lastPaymentAmount.toInt(),
                lastPaymentDate = lastPaymentDate
            )
        } catch (e: TypeCastException) {
            throw e
        }
    }
}