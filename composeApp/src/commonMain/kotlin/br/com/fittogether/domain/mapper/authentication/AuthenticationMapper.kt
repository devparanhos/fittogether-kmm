package br.com.fittogether.domain.mapper.authentication

import br.com.fittogether.data.remote.dto.request.authentication.LoginRequest
import br.com.fittogether.domain.model.authentication.Login

fun Login.toDto() : LoginRequest {
    return LoginRequest(
        email = this.email,
        password = this.password
    )
}