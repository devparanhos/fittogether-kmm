package br.com.fittogether.domain.model.signup

import br.com.fittogether.core.enums.UserStatus

data class VerifyEmail(
    val email: String? = null,
    val status: UserStatus? = null,
    val sendingCode: Boolean? = null,
    val message: String? = null
)