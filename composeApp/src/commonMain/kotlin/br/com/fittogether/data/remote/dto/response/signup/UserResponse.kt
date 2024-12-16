package br.com.fittogether.data.remote.dto.response.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("registration_step")
    val registrationStep: String? = null,

    @SerialName("username")
    val username: String? = null,

    @SerialName("gender")
    val gender: String? = null,

    @SerialName("experience")
    val experience: String? = null,

    @SerialName("goals")
    val goals: List<GoalResponse>? = null,

    @SerialName("exercises")
    val exercises: List<ExercisesResponse>? = null,

    @SerialName("preference")
    val preference: PreferenceResponse? = null,

    @SerialName("registration_status")
    val status: String? = null
)