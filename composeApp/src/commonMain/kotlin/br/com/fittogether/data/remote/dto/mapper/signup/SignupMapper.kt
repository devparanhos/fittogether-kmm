package br.com.fittogether.data.remote.dto.mapper.signup

import br.com.fittogether.data.remote.dto.response.VerifyEmailResponse
import br.com.fittogether.domain.model.signup.VerifyEmail

fun VerifyEmailResponse.toDomain() : VerifyEmail {
    return VerifyEmail(
        status = this.status,
        sendingCode = this.sendingCode,
        message = this.message
    )
}