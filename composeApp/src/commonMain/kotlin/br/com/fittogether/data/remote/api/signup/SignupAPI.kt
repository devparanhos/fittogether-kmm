package br.com.fittogether.data.remote.api.signup

import br.com.fittogether.data.remote.dto.request.signup.CreateUserRequest
import br.com.fittogether.data.remote.dto.request.signup.ValidateCodeRequest
import br.com.fittogether.data.remote.dto.request.signup.VerifyEmailRequest

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class SignupAPI(
    private val httpClient: HttpClient
) {
    suspend fun verifyEmail(request: VerifyEmailRequest) : HttpResponse {
        return httpClient.post("users/validate-email") {
            setBody(request)
        }
    }

    suspend fun validateCode(request: ValidateCodeRequest) : HttpResponse {
        return httpClient.post("users/validate-code") {
            setBody(request)
        }
    }

    suspend fun createUser(request: CreateUserRequest) : HttpResponse {
        return httpClient.post("users") {
            setBody(request)
        }
    }
}