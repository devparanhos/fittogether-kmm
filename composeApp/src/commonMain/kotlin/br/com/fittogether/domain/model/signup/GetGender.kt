package br.com.fittogether.domain.model.signup

import br.com.fittogether.domain.model.gender.Gender

data class GetGender(
    val title: String? = null,
    val genders: List<Gender>? = null
)