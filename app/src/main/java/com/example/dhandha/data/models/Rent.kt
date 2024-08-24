package com.example.dhandha.data.models

import android.util.Log
import com.example.dhandha.data.local.rent.RentUserEntity
import com.example.dhandha.helper.convertStringDateToLong
import com.example.dhandha.helper.getTodayDate
import java.util.UUID

data class RentUserListCell(val name: String, val phone: String,
                            val pendingAmount: Int, val rentAmount: Int,
                            val expiryDate: String)

data class RentUser (val name: String, val phone: String,
                     val pendingAmount: String, val rentAmount: String,
                     val startDate: String, val expiryDate: String,
                     val joinedDate: String,
                     val lastPaymentDate: String, val lastPaymentAmount: String)


data class CreateRentUser(
    val id: UUID,
    val image: String?,
    val name: String,
    val phone: String,
    val address: String,
    val rent: String,
    val pending: String,
    val advance: String,
    val security: String,
    val paidAmount: String,
    val joinedDate: String,
    val agreementStart: String,
    val agreementEnd: String,
    val rentStart: String,
    val rentEnd: String,
    val lastPaymentDate: String
) {


    // Only to be used from viewModels
    fun toEntity() : RentUserEntity {
        // validating everything
        try {
            return  RentUserEntity(
                id = id,
                phone = phone,
                rentAmount = rent.toInt() ,
                address = address,
                isActive = true,
                name = name,
                agreementEndDate = convertStringDateToLong(agreementEnd) ?: throw  Error("this is bad"),
                agreementStartDate =  convertStringDateToLong(agreementStart) ?: throw Error(""),
                joinedDate = convertStringDateToLong(joinedDate) ?: throw Error(""),
                pendingAmount =  pending.toInt(),
                securityAmountPaid = security.toInt(),
                rentExpiryDate = convertStringDateToLong(rentEnd) ?: throw Error(""),
                rentStartDate = convertStringDateToLong(rentStart) ?: throw Error(""),
                advanceAmountPaid = advance.toInt(),
                image = image,
                amountPaid = paidAmount.toInt()
            )
        } catch (error: Error) {
            Log.d("TAG", "toEntity: ${error.localizedMessage}")
            throw  error
        }
    }
}