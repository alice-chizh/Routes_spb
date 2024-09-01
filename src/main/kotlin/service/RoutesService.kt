package service

import database.entity.Route
import database.table.RouteStops
import database.table.Routes
import database.table.Stops
import me.piruin.geok.geometry.Point
import model.PointDto
import model.RouteDto
import model.StopDto
import org.jetbrains.exposed.sql.selectAll

object RoutesService {
    fun getNearbyStops(point: PointDto, distance: Int): List<StopDto> {
        val stops = Stops.selectAll().map { stopRow ->
            val id = stopRow[Stops.id].value
            val latitude = stopRow[Stops.latitude]
            val longitude = stopRow[Stops.longitude]
            StopDto(id, latitude, longitude)
        }.filter { stop ->
            val stopGeoPoint = Point(stop.latitude, stop.longitude)
            val geoPoint = Point(point.latitude, point.longitude)
            geoPoint.distanceTo(stopGeoPoint) < distance
        }
        return stops
    }

    fun getRoutesByStops(stops: List<StopDto>): List<RouteDto> {
        val ids = stops.map { stop -> stop.id }
        val routeRows = Routes.innerJoin(RouteStops).selectAll().where {
            RouteStops.stop inList ids
        }
        val routes = Route.wrapRows(routeRows)
        val routesDto = routes.map { route ->
            RouteDto(
                id = route.id.value,
                name = route.name,
                transportType = route.transportType,
                stops = route.stops.map { stop ->
                    StopDto(
                        id = stop.id.value,
                        latitude = stop.latitude,
                        longitude = stop.longitude
                    )
                },
                shapes = route.items.map { item ->
                    item.shape.split(", ").map { latLong ->
                        latLong.split(" ").map { coordinate ->
                            coordinate.toDouble()
                        }.reversed()
                    }
                }
            )
        }
        return routesDto
    }
}