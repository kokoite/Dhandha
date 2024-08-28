package com.example.dhandha.services

sealed class Service {
    data object Rent: Service()
    data object Coaching: Service()
    data object Gym: Service()
    data object Library: Service()
}