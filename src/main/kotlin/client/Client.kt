package client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    engine {
        https {
            trustManager = object: X509TrustManager {
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) { }
                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) { }
                override fun getAcceptedIssuers(): Array<X509Certificate>? = null
            }
        }
    }
}