package br.com.fittogether.data.remote.wrapper

import br.com.fittogether.domain.mapper.error.toDomain
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException

import kotlinx.serialization.json.Json

class ApiWrapper {
    suspend inline operator fun <reified D, reified R> invoke(
        request: HttpResponse,
        crossinline mapper: (R) -> D,
    ): ResultAPI<D?> {
        try {
            return when (request.status.value) {
                in 200..299 -> ResultAPI(
                    data = mapper(request.body<R>())
                )

                else -> {
                    ResultAPI(
                        data = null,
                        error = Json.decodeFromString<ApiErrorResponse>(request.bodyAsText())
                    )
                }
            }
        } catch (e: ClientRequestException) {
            throw e
        } catch (e: ServerResponseException) {
            throw e
        } catch (e: TimeoutCancellationException) {
            throw e
        } catch (e: IOException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}