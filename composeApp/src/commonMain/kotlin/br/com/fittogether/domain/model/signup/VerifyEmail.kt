package br.com.fittogether.domain.model.signup

data class VerifyEmail(
    val email: String = "",
    val status: String? = "",
    val sendingCode: Boolean? = false,
    val message: String? = ""
)