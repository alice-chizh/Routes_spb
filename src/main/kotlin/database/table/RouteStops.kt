package database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object RouteStops : IntIdTable("route_stop") {
    val route = reference("route_id", Routes)
    val stop = reference("stop_id", Stops)
}