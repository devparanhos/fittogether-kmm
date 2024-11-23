package br.com.fittogether.domain.model.user

import br.com.fittogether.core.enums.RegistrationStep
import br.com.fittogether.data.remote.dto.response.signup.PreferenceResponse
import br.com.fittogether.domain.model.exercise.Exercise
import br.com.fittogether.domain.model.goal.Goal

data class User(
    val id: Int? = null,
    val name: String? = null,
    val registrationStep: RegistrationStep? = null,
    val username: String? = null,
    val gender: String? = null,
    val experience: String? = null,
    val goals: List<Goal>? = null,
    val exercises: List<Exercise>? = null,
    val preference: PreferenceResponse? = null
)