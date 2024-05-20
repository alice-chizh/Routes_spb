package database.entity

import database.table.Items
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Item(id: EntityID<Int>) : IntEntity(id) {
    var order by Items.order
    var direction by Items.direction

    var route by Route referencedOn Items.route

    var from by Stop referencedOn Items.from
    var to by Stop referencedOn Items.to

    var shape by Items.shape

    companion object : IntEntityClass<Item>(Items)
}