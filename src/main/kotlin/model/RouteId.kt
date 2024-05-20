package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RouteId(
    @SerialName("Id")
    val id: Int,

    @SerialName("TransportType")
    val transportType: String,

    @SerialName("ShortName")
    val shortName: String
)