package database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Routes : IntIdTable("routes") {
    val name = text("name")
    val transportType = text("transport_type")
}