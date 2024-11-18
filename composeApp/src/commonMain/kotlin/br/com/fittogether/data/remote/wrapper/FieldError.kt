package br.com.fittogether.data.remote.wrapper

import kotlinx.serialization.Serializable

@Serializable
data class FieldError(
    val field: String,
    val message: String
)