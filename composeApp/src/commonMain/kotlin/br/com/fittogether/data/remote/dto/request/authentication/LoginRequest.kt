package br.com.fittogether.data.remote.dto.request.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("email")
    val email: String? = null,

    @SerialName("password")
    val password: String? = null
)