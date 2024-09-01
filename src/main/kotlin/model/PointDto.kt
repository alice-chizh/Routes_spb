package model

import kotlinx.serialization.Serializable

@Serializable
data class PointDto(
    val latitude : Double,
    val longitude : Double
)