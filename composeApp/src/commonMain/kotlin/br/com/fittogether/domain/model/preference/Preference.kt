package br.com.fittogether.domain.model.preference

import br.com.fittogether.domain.model.gender.Gender
import kotlinx.serialization.Serializable

@Serializable
data class Preference(
    val id: Int? = null,
    val startAge: Int? = null,
    val endAge: Int? = null,
    val radiusDistance: Int? = null,
    val genders: List<Gender>? = null
)