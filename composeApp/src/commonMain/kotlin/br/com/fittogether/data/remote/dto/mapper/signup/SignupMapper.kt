package br.com.fittogether.data.remote.dto.mapper.signup

import br.com.fittogether.data.remote.dto.response.CreateUserResponse
import br.com.fittogether.data.remote.dto.response.ValidateCodeResponse
import br.com.fittogether.data.remote.dto.response.VerifyEmailResponse
import br.com.fittogether.domain.model.signup.User
import br.com.fittogether.domain.model.signup.ValidateCode
import br.com.fittogether.domain.model.signup.VerifyEmail

fun VerifyEmailResponse.toDomain() : VerifyEmail {
    return VerifyEmail(
        status = this.status,
        sendingCode = this.sendingCode,
        message = this.message
    )
}

fun ValidateCodeResponse.toDomain() : ValidateCode {
    return ValidateCode(
        registrationStep = this.registrationStep,
        userId = this.userId
    )
}

fun CreateUserResponse.toDomain() : User {
    return User(
        accessToken = this.accessToken
    )
}