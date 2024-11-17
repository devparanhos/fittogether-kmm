package br.com.fittogether.presentation.navigation.route.start

import kotlinx.serialization.Serializable

sealed class StartRoutes {

    @Serializable
    data object Graph : StartRoutes()

    @Serializable
    data object Welcome : StartRoutes()

    @Serializable
    data object Onboarding : StartRoutes()

    @Serializable
    data object Start : StartRoutes()
}