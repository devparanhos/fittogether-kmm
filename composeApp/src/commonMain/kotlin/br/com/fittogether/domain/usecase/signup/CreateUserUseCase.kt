package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.signup.toDto
import br.com.fittogether.domain.model.signup.CreateUser
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.repository.signup.SignupRepository

class CreateUserUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(
        email: String,
        name: String,
        birthdate: String,
        cellphone: String,
        confirmPassword: String,
        password: String,
        username: String
    ) : ResultAPI<User?> {
        return repository.createUser(
            request = CreateUser(
                name = name,
                email = email,
                cellphone = cellphone,
                birthdate = birthdate,
                confirmPassword = confirmPassword,
                password = password,
                username = username
            ).toDto()
        )
    }
}