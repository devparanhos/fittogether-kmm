package br.com.fittogether.data.remote.dto.request.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidateCodeRequest(
    @SerialName("email")
    val email: String? = null,

    @SerialName("code")
    val code: String? = null
)