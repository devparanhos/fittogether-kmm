package br.com.fittogether.data.repository.authentication

import br.com.fittogether.data.remote.api.authentication.AuthenticationAPI
import br.com.fittogether.data.remote.dto.mapper.signup.toDomain
import br.com.fittogether.data.remote.dto.request.authentication.LoginRequest
import br.com.fittogether.data.remote.dto.response.signup.CreateUserResponse
import br.com.fittogether.data.remote.wrapper.ApiWrapper
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.repository.authentication.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authenticationAPI: AuthenticationAPI,
    private val wrapper: ApiWrapper
) : AuthenticationRepository {
    override suspend fun login(request: LoginRequest): ResultAPI<User?> {
        return wrapper(
            request = authenticationAPI.login(request = request),
            mapper = CreateUserResponse::toDomain
        )
    }
}