package model

import kotlinx.serialization.Serializable

@Serializable
data class RouteDto(
    val id: Int,
    val name: String,
    val transportType: String,
    val stops: List<StopDto>,
    val shapes: List<List<List<Double>>>
)