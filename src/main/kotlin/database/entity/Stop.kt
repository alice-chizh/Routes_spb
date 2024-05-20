package database.entity

import database.table.RouteStops
import database.table.Stops
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Stop(id: EntityID<Int>) : IntEntity(id) {
    var latitude by Stops.latitude
    var longitude by Stops.longitude

    var routes by Route via RouteStops

    companion object : IntEntityClass<Stop>(Stops)
}