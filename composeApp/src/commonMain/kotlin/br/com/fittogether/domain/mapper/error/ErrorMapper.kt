package br.com.fittogether.domain.mapper.error

import br.com.fittogether.data.remote.wrapper.ApiErrorResponse
import br.com.fittogether.data.remote.wrapper.FieldErrorResponse
import br.com.fittogether.domain.model.error.ApiError
import br.com.fittogether.domain.model.error.FieldError

fun ApiErrorResponse.toDomain() : ApiError {
    return ApiError(
        statusCode = this.statusCode,
        internalCode = this.internalCode,
        message = this.message,
        errors = this.errors?.map { it.toDomain() }
    )
}

fun FieldErrorResponse.toDomain() : FieldError {
    return FieldError(
        field = this.field,
        message = this.message
    )
}