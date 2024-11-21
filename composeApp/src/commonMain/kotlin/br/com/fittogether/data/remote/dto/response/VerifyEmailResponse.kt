package br.com.fittogether.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailResponse(
    @SerialName("status")
    val status: String,

    @SerialName("sending_code")
    val sendingCode: Boolean? = null,

    @SerialName("message")
    val message: String? = null
)