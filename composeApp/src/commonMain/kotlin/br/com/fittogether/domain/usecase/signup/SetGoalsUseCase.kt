package br.com.fittogether.domain.usecase.signup

import br.com.fittogether.data.remote.dto.request.registration.goals.SetGoalsRequest
import br.com.fittogether.data.remote.wrapper.ResultAPI
import br.com.fittogether.domain.mapper.signup.toDto
import br.com.fittogether.domain.model.goal.Goal
import br.com.fittogether.domain.model.user.User
import br.com.fittogether.domain.repository.signup.SignupRepository

class SetGoalsUseCase(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(userId: Int?, goals: List<Goal>?) : ResultAPI<User?> {
        return repository.setGoals(
            request = SetGoalsRequest(
                userId = userId,
                goals = goals?.map { it.toDto() }
            )
        )
    }
}