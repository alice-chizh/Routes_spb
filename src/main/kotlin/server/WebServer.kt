package server

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.PointDto
import org.jetbrains.exposed.sql.transactions.transaction
import service.RoutesService
import java.io.File

fun Route.mainPage() {
    get("/") {
        call.respondFile(File("index.html"))
    }
}

fun Route.nearby() {
    get("/nearby") {
        val latitude = call.request.queryParameters["latitude"]?.toDouble()
        val longitude = call.request.queryParameters["longitude"]?.toDouble()
        if (latitude != null && longitude != null) {
            val point = PointDto(latitude, longitude)
            val distance = 500
            val routes = transaction {
                val stops = RoutesService.getNearbyStops(point, distance)
                RoutesService.getRoutesByStops(stops)
            }
            val body = Json.encodeToString(routes)
            call.respond(body)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    //localhost:8080/nearby?latitude=30.29&longitude=30.28
}
