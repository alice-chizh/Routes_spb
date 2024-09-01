package client

import model.RouteId
import model.RouteInfo
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

object RouteService {
    suspend fun getAllRoutes(): List<RouteId> { //получает с API транспорта спб все маршруты
        val url = "https://nts-admin.orgp.spb.ru/api/visary/operator/route"

        val routeIds = mutableListOf<RouteId>()
        var skip = 0
        while (true) {
            println("Making request with skip = $skip")
            val response: HttpResponse = client.post(url) {
                val formParameters = parameters {
                    append("take", "20")
                    append("skip", skip.toString())
                }
                setBody(FormDataContent(formParameters))
            }

            if (response.status != HttpStatusCode.OK) break
            val routesReceived = response.body<List<RouteId>>()

            println("Received ${routesReceived.size} routes")

            routeIds += routesReceived
            skip += 20
        }
        return routeIds
    }

    suspend fun getRouteById(id: Int): RouteInfo {  //получает маршрут по id
        val urlByRoute = "https://nts-admin.orgp.spb.ru/api/visary/stop/byRoutes?routesId=$id"
        return client.get(urlByRoute).body<List<RouteInfo>>().first()
    }
}
