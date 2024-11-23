package br.com.fittogether.data.remote.dto.mapper.signup

import br.com.fittogether.core.enums.RegistrationStep
import br.com.fittogether.core.enums.UserStatus
import br.com.fittogether.data.remote.dto.response.signup.CreateUserResponse
import br.com.fittogether.data.remote.dto.response.signup.ValidateCodeResponse
import br.com.fittogether.data.remote.dto.response.signup.VerifyEmailResponse
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.model.signup.ValidateCode
import br.com.fittogether.domain.model.signup.VerifyEmail

fun VerifyEmailResponse.toDomain() : VerifyEmail {
    return VerifyEmail(
        status = UserStatus.findByValue(this.status),
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