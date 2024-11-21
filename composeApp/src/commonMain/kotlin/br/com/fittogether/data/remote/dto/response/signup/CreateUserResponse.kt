package br.com.fittogether.data.remote.dto.response.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("access_token")
    val accessToken: String? = null,

    @SerialName("registration_step")
    val registrationStep: String? = null
)