package br.com.fittogether.data.remote.dto.response.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGoalsResponse(
    @SerialName("title")
    val title: String? = null,

    @SerialName("goals")
    val goals : List<GoalResponse>? = null
)