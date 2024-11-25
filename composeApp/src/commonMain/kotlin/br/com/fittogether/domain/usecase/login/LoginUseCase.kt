package br.com.fittogether.domain.usecase.login

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.authentication.toDto
import br.com.fittogether.domain.model.authentication.Login
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.repository.authentication.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String) : ResultAPI<User?> {
        return repository.login(
            request = Login(
                email = email,
                password = password
            ).toDto()
        )
    }
}