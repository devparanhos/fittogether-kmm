package br.com.fittogether.data.remote.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldErrorResponse(
    @SerialName("field")
    val field: String? = null,

    @SerialName("message")
    val message: String? = null
)