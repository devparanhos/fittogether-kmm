package br.com.fittogether.presentation.feature.signup.gender.state

import br.com.fittogether.domain.model.gender.Gender

data class GenderState(
    val navigateToStart: Boolean = false,
    val isLoading: Boolean = false,
    val genders: List<Gender>? = null,
    val title: String? = "",
    val selectedGender: Gender? = null,
    val isRequesting: Boolean = false,
    val navigateToGoal: Boolean = false
)