package database.entity

import database.table.RouteStops
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RouteStop(id: EntityID<Int>) : IntEntity(id) {
    var route by Route referencedOn RouteStops.route
    var stop by Stop referencedOn RouteStops.stop

    companion object : IntEntityClass<RouteStop>(RouteStops)
}