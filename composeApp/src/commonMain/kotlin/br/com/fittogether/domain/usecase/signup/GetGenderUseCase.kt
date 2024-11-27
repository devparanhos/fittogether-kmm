package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.signup.GetGender
import br.com.fittogether.domain.repository.signup.SignupRepository

class GetGenderUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke() : ResultAPI<GetGender?> {
        return repository.getGenders()
    }
}