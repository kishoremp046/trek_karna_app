package com.trekkarnataka.app.data

data class Trek(
    val id: String,
    val name: String,
    val district: String,
    val elevationM: Int,
    val difficulty: String,
    val distanceKm: Double,
    val duration: String,
    val bestSeason: String,
    val description: String,
    val bookingUrl: String,
    val photos: List<String>
)
