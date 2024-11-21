package br.com.fittogether.domain.model.error

data class ApiError(
    val message: String? = null,
    val internalCode: String? = null,
    val statusCode: Int? = null,
    val errors: List<FieldError>? = null
)