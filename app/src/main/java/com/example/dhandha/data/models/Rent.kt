package com.example.dhandha.data.models

data class RentUserListCell(val name: String, val phone: String,
                            val pendingAmount: Int, val rentAmount: Int,
                            val expiryDate: String)

data class RentUser (val name: String, val phone: String,
                     val pendingAmount: Int, val rentAmount: Int,
                     val startDate: String, val expiryDate: String,
                     val joinedDate: String, val utilityAmount: Int,
                     val lastPaymentDate: String, val lastPaymentAmount: Int)