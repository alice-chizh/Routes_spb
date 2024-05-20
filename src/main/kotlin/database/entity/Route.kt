package database.entity

import database.table.RouteStops
import database.table.Routes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Route(id: EntityID<Int>) : IntEntity(id) {
    var name by Routes.name
    var transportType by Routes.transportType

    val stops by Stop via RouteStops

    companion object : IntEntityClass<Route>(Routes)
}