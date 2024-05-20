import database.table.Items
import database.table.RouteStops
import database.table.Routes
import database.table.Stops
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import service.CollectingService

suspend fun main() {
    Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "root", password = "")

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Routes, Stops, RouteStops, Items)
    }

    CollectingService.collect()
}