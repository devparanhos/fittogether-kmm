package br.com.fittogether.data.remote.dto.request.registration.goals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SetGoalsRequest(
    @SerialName("userId")
    val userId: Int? = null,

    @SerialName("goals")
    val goals: List<GoalRequest>? = null
)