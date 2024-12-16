package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.model.signup.GetGoal
import br.com.fittogether.domain.repository.signup.SignupRepository

class GetGoalUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke() : ResultAPI<GetGoal?> {
        return repository.getGoals()
    }
}