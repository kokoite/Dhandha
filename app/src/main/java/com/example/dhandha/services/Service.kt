package com.example.dhandha.services

sealed class Service {
    object Rent: Service()
    object Coaching: Service()
    object Gym: Service()
    object Library: Service()
}