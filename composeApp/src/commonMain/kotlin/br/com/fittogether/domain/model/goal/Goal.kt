package br.com.fittogether.domain.model.goal

import kotlinx.serialization.Serializable

@Serializable
data class Goal(
    val id: Int? = null,
    val name: String? = null,
    val icon: String? = null
)