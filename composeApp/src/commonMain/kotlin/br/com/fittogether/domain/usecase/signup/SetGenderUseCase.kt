package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.signup.toDto
import br.com.fittogether.domain.model.gender.Gender
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.repository.signup.SignupRepository

class SetGenderUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(userId: Int?, genderId: Int?) : ResultAPI<User?> {
        return repository.setGender(
            userId = userId,
            request = Gender(id = genderId).toDto()
        )
    }
}