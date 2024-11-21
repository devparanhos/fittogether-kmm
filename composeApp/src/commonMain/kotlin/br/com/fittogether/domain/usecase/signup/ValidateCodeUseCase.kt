package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.signup.toDto
import br.com.fittogether.domain.model.signup.ValidateCode
import br.com.fittogether.domain.repository.signup.SignupRepository

class ValidateCodeUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(email: String, code: String) : ResultAPI<ValidateCode?>{
        return repository.validateCode(
            request = ValidateCode(
                email = email,
                code = code
            ).toDto()
        )
    }
}