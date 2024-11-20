package br.com.fittogether.domain.mapper

import br.com.fittogether.data.remote.dto.request.CreateUserRequest
import br.com.fittogether.data.remote.dto.request.ValidateCodeRequest
import br.com.fittogether.data.remote.dto.request.VerifyEmailRequest
import br.com.fittogether.domain.model.signup.CreateUser
import br.com.fittogether.domain.model.signup.ValidateCode
import br.com.fittogether.domain.model.signup.VerifyEmail

fun VerifyEmail.toDto() : VerifyEmailRequest {
    return VerifyEmailRequest(
        email = this.email
    )
}

fun ValidateCode.toDto() : ValidateCodeRequest {
    return ValidateCodeRequest(
        email = this.email,
        code = this.code
    )
}

fun CreateUser.toDto() : CreateUserRequest {
    return CreateUserRequest(
        email = this.email,
        name = this.name,
        password = this.password,
        confirmPassword = this.confirmPassword,
        cellphone = this.cellphone,
        birthdate = this.birthdate,
        username = this.email
    )
}