package br.com.fittogether.domain.repository.authentication

import br.com.fittogether.data.remote.dto.request.authentication.LoginRequest
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.user.User

interface AuthenticationRepository {
    suspend fun login(request: LoginRequest) : ResultAPI<User?>
}