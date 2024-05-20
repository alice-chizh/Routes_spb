package database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Items : IntIdTable("items") {
    val direction = integer("direction")

    val order = integer("order")
    val route = reference("route_id", Routes)

    val from = reference("from_stop_id", Stops)
    val to = reference("to_stop_id", Stops)

    val shape = text("shape")
}