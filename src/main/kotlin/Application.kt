import database.table.Items
import database.table.RouteStops
import database.table.Routes
import database.table.Stops
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import server.mainPage
import server.nearby
import service.CollectingService

suspend fun main() {
    Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "root", password = "")

    transaction {
        SchemaUtils.createMissingTablesAndColumns(Routes, Stops, RouteStops, Items)
    }

    CollectingService.collect()

    embeddedServer(Netty, port = 8080) {
        routing {
            nearby()
            mainPage()
        }
    }.start(wait = true)
}