package com.example.dhandha.domain

interface UserRepository {
    fun fetchUsers()
    fun createUser()
    fun editUser()
    fun deleteUser()
}