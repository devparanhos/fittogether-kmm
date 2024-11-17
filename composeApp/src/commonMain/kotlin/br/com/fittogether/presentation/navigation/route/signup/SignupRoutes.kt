package br.com.fittogether.presentation.navigation.route.signup

import kotlinx.serialization.Serializable

sealed class SignupRoutes {
    @Serializable
    data object Graph : SignupRoutes()

    @Serializable
    data object VerifyEmail : SignupRoutes()
}