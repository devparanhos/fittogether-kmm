package br.com.fittogether.domain.model.error

data class FieldError(
    val field: String? = null,
    val message: String? = null
)