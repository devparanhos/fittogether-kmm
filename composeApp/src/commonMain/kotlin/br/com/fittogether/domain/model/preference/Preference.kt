package br.com.fittogether.domain.model.preference

import br.com.fittogether.domain.model.gender.Gender

data class Preference(
    val id: Int? = null,
    val startAge: Int? = null,
    val endAge: Int? = null,
    val radiusDistance: Int? = null,
    val genders: List<Gender>? = null
)