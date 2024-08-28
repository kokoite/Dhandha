package com.example.dhandha.data.models

import android.util.Log
import com.example.dhandha.data.local.entity.LibraryUserEntity
import com.example.dhandha.helper.convertStringDateToLong
import java.util.UUID


data class LibraryUserListCell(val id: UUID, val name: String, val phone: String,
                            val pendingAmount: String, val feesAmount: String,
                            val expiryDate: String, val image: String?)

data class LibraryUser (val id: UUID, val name: String, val phone: String,
                     val pendingAmount: String, val feesAmount: String,
                     val startDate: String, val expiryDate: String,
                     val joinedDate: String,
                        val image: String?, val address: String, val isActive: Boolean,
                     val lastPaymentDate: String, val lastPaymentAmount: String) {

    fun toEntity(): LibraryUserEntity {
        try {
            val expiry = convertStringDateToLong(expiryDate) ?: 0
            val joined = convertStringDateToLong(joinedDate) ?: 0
            val startDate = convertStringDateToLong(startDate) ?: 0
            val lastPaymentAmount = lastPaymentAmount.toInt()
            val lastPaymentDate = convertStringDateToLong(lastPaymentDate) ?: 0
            val pendingAmount = pendingAmount.toInt()
            val fees = feesAmount.toInt()
            return LibraryUserEntity(
                id,
                image = image,
                lastPaymentDate = lastPaymentDate,
                lastPaymentAmount = lastPaymentAmount,
                joinedDate = joined,
                address = address, isActive = isActive,
                name = name, phone = phone, pendingAmount = pendingAmount,
                fees = fees, monthExpiringDate = expiry, monthStartingDate = startDate
            )

        } catch (error: TypeCastException) {
            Log.d("TAG", "toEntity: ${error.localizedMessage}")
            throw error
        } catch (error: RuntimeException) {
            Log.d("TAG", "toEntity: ${error.localizedMessage}")
            throw error
        }
    }
}