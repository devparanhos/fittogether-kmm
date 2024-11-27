package br.com.fittogether.data.remote.api.authentication

import br.com.fittogether.data.remote.client.HttpClientManager
import br.com.fittogether.data.remote.dto.request.authentication.LoginRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class AuthenticationAPI(
    private val httpClientManager: HttpClientManager
) {
    suspend fun login(request: LoginRequest) : HttpResponse {
        return httpClientManager.getHttpClient().post("users/login") {
            setBody(request)
        }
    }
}