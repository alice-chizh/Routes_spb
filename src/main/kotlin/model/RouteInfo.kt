package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RouteInfo(
    @SerialName("RouteId")
    val id: Int,

    @SerialName("Directions")
    val directions: List<Direction>
)

@Serializable
data class Direction(
    @SerialName("Direction")
    val direction: Int,

    @SerialName("Items")
    val items: List<Item>
)

@Serializable
data class Item(
    @SerialName("Order")
    val order: Int,

    @SerialName("StartStopId")
    val startStopId: Int,

    @SerialName("StartStopLatitude")
    val startStopLatitude: Double,

    @SerialName("StartStopLongitude")
    val startStopLongitude: Double,

    @SerialName("FinishStopId")
    val finishStopId: Int,

    @SerialName("FinishStopLatitude")
    val finishStopLatitude: Double,

    @SerialName("FinishStopLongitude")
    val finishStopLongitude: Double,

    @SerialName("Shape")
    val shape: String
)