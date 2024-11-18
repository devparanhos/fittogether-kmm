package br.com.fittogether.data.remote.wrapper

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val message: String?,
    val internalCode: String,
    val statusCode: Int,
    val errors: String? = null
)