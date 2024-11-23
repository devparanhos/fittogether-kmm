package br.com.fittogether.domain.model.gender

import kotlinx.serialization.Serializable

@Serializable
data class Gender(
    val id: Int? = null,
    val name: String? = null,
    val icon: String? = null
)