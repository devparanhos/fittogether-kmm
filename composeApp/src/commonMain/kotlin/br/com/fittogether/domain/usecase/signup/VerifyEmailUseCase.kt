package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.signup.toDto
import br.com.fittogether.domain.model.signup.VerifyEmail
import br.com.fittogether.domain.repository.signup.SignupRepository

class VerifyEmailUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(email: String) : ResultAPI<VerifyEmail?> {
        return repository.verifyEmail(request = VerifyEmail(email = email).toDto())
    }
}