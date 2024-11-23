package br.com.fittogether.domain.model.exercise

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int? = null,
    val name: String? = null,
    val icon: String? = null
)