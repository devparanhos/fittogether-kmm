package br.com.fittogether.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidateCodeRequest(
    @SerialName("email")
    val email: String?,

    @SerialName("code")
    val code: String?
)