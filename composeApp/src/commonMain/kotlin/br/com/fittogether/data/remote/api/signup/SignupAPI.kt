package br.com.fittogether.data.remote.api.signup

import br.com.fittogether.data.remote.client.HttpClientManager
import br.com.fittogether.data.remote.dto.request.signup.CreateUserRequest
import br.com.fittogether.data.remote.dto.request.signup.SetGenderRequest
import br.com.fittogether.data.remote.dto.request.signup.ValidateCodeRequest
import br.com.fittogether.data.remote.dto.request.signup.VerifyEmailRequest

import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class SignupAPI(
    private val httpClientManager: HttpClientManager
) {
    suspend fun verifyEmail(request: VerifyEmailRequest) : HttpResponse {
        return httpClientManager.getHttpClient().post("users/validate-email") {
            setBody(request)
        }
    }

    suspend fun validateCode(request: ValidateCodeRequest) : HttpResponse {
        return httpClientManager.getHttpClient().post("users/validate-code") {
            setBody(request)
        }
    }

    suspend fun createUser(request: CreateUserRequest) : HttpResponse {
        return httpClientManager.getHttpClient().post("users") {
            setBody(request)
        }
    }

    suspend fun getGenders() : HttpResponse {
        return httpClientManager.getHttpClient().get("registration/screen/genders")
    }

    suspend fun setGender(userId: Int?, request: SetGenderRequest) : HttpResponse {
        return httpClientManager.getHttpClient().post("registration/$userId/gender") {
            setBody(request)
        }
    }
}