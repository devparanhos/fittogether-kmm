package br.com.fittogether.presentation.navigation.route.registration

import kotlinx.serialization.Serializable

sealed class RegistrationRoutes {
    @Serializable
    data object Graph : RegistrationRoutes()

    @Serializable
    data object Gender : RegistrationRoutes()

    @Serializable
    data object Goal : RegistrationRoutes()
}