package br.com.fittogether.data.remote.api.authentication

import br.com.fittogether.data.remote.dto.request.authentication.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class AuthenticationAPI(
    private val httpClient: HttpClient
) {
    suspend fun login(request: LoginRequest) : HttpResponse {
        return httpClient.post("users/login") {
            setBody(request)
        }
    }
}