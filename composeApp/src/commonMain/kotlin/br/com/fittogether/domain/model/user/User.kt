package br.com.fittogether.domain.model.user

import br.com.fittogether.core.enums.Gender
import br.com.fittogether.core.enums.RegistrationStep
import br.com.fittogether.domain.model.exercise.Exercise
import br.com.fittogether.domain.model.goal.Goal
import br.com.fittogether.domain.model.preference.Preference
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class User(
    val accessToken: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val registrationStep: RegistrationStep? = null,
    val username: String? = null,
    val gender: Gender? = null,
    val experience: String? = null,
    val goals: List<Goal>? = null,
    val exercises: List<Exercise>? = null,
    val preference: Preference? = null
)