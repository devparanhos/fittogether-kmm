package br.com.fittogether.data.remote.dto.request.registration.goals

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoalRequest(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("icon")
    val icon: String? = null
)