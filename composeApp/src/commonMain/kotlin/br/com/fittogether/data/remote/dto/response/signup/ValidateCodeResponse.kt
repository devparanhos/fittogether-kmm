package br.com.fittogether.data.remote.dto.response.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidateCodeResponse(
    @SerialName("user_id")
    val userId: Int? = null,

    @SerialName("registration_step")
    val registrationStep: String? = null
)