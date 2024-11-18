package br.com.fittogether.data.remote.wrapper

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class ApiWrapper {
    suspend inline operator fun <reified D, reified R> invoke(
        request: HttpResponse,
        crossinline mapper: (R) -> D,
    ): ResultAPI<D?> {
        return when (request.status.value) {
            in 200..299 -> ResultAPI(
                data = mapper(request.body<R>())
            )

            else -> ResultAPI(
                data = null,
                error = Json.decodeFromString<ApiError>(request.bodyAsText())
            )
        }
    }
}