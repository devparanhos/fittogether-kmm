package br.com.fittogether.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VerifyEmailRequest(
    @SerialName("email")
    val email: String = ""
)