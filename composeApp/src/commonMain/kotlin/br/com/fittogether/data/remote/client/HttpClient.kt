package br.com.fittogether.data.remote.client

import br.com.fittogether.core.controller.PreferenceController
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine) : HttpClient {
    return HttpClient(engine = engine) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(DefaultRequest) {
            url(PreferenceController.BASE_URL)
            contentType(ContentType.Application.Json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 300000
        }
    }
}