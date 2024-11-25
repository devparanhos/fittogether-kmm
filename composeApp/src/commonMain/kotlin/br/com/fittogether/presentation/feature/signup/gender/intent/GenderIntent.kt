package br.com.fittogether.presentation.feature.signup.gender.intent

sealed class GenderIntent {
    data object Logout : GenderIntent()
}