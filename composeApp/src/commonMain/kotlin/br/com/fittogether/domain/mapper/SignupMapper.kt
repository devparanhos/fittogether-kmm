package br.com.fittogether.domain.mapper

import br.com.fittogether.data.remote.dto.request.VerifyEmailRequest
import br.com.fittogether.domain.model.signup.VerifyEmail

fun VerifyEmail.toDto() : VerifyEmailRequest {
    return VerifyEmailRequest(
        email = this.email
    )
}