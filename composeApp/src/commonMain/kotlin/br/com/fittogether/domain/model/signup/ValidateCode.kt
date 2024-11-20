package br.com.fittogether.domain.model.signup

data class ValidateCode(
    val code: String? = null,
    val email: String? = null,
    val userId: Int? = null,
    val registrationStep: String? = null
)