package model

import kotlinx.serialization.Serializable

@Serializable
data class StopDto(
    val id: Int,
    val latitude: Double,
    val longitude: Double
)
