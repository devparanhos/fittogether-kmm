package br.com.fittogether.data.remote.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("message")
    val message: String? = null,

    @SerialName("internalCode")
    val internalCode: String? = null,

    @SerialName("statusCode")
    val statusCode: Int? = null,

    @SerialName("errors")
    val errors: List<FieldError>? = null
)