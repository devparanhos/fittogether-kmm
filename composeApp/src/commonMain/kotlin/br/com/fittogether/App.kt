package br.com.fittogether

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.core.enums.UserStatus
import br.com.fittogether.presentation.navigation.host.app.AppNavHost
import br.com.fittogether.presentation.navigation.route.registration.RegistrationRoutes
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes
import br.com.fittogether.presentation.ui.theme.FitTogetherTheme
import org.koin.compose.koinInject

@Suppress("IMPLICIT_CAST_TO_ANY")
@Composable
fun App() {
    FitTogetherTheme {
        val preferences = koinInject<PreferenceController>()
        val graph = when {
            preferences.getUser()?.status == UserStatus.REGISTRATION -> RegistrationRoutes.Graph
            preferences.getUser()?.status == UserStatus.CREATED -> SignupRoutes.Graph
            preferences.hasOnboarding() -> StartRoutes.Graph
            else -> StartRoutes.Graph
        }

        AppNavHost(
            navHostController = rememberNavController(), graph = graph
        )
    }
}