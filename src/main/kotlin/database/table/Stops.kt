package database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Stops : IntIdTable("stop") {
    val latitude = double("latitude")
    val longitude = double("longitude")
}