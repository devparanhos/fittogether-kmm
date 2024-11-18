package br.com.fittogether.data.remote.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("message")
    val message: String?,

    @SerialName("internalCode")
    val internalCode: String,

    @SerialName("statusCode")
    val statusCode: Int,

    @SerialName("errors")
    val errors: List<FieldError>? = null
)