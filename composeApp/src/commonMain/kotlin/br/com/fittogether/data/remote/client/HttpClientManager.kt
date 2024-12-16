package br.com.fittogether.data.remote.client

import br.com.fittogether.core.controller.PreferenceController

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json

class HttpClientManager(
    private val engine: HttpClientEngine,
    private val preferenceController: PreferenceController
) {
    private lateinit var httpClient: HttpClient

    init {
        createHttpClient()
    }

    private fun createHttpClient() : HttpClient {
        Napier.base(DebugAntilog())

        httpClient = HttpClient(engine = engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(message = message, tag = "HTTP call")
                    }
                }
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
                requestTimeoutMillis = 15000
            }
            install(Auth){
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = preferenceController.getToken() ?: "",
                            refreshToken = preferenceController.getToken() ?: ""
                        )
                    }
                }
            }
        }

        return httpClient
    }

    fun refreshHttpClient() {
        httpClient.close()
        httpClient = createHttpClient()
    }

    fun getHttpClient() = httpClient
}


