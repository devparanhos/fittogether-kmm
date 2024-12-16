package br.com.fittogether.presentation.feature.registration.gender.intent

import br.com.fittogether.domain.model.gender.Gender

sealed class GenderIntent {
    data object Logout : GenderIntent()
    data object SaveGender : GenderIntent()

    data class SelectGender(val gender: Gender) : GenderIntent()
}