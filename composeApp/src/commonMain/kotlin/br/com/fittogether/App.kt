package br.com.fittogether

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.presentation.navigation.host.app.AppNavHost
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes
import br.com.fittogether.presentation.ui.theme.FitTogetherTheme
import org.koin.compose.koinInject

@Composable
fun App() {
    FitTogetherTheme {
        val preferences = koinInject<PreferenceController>()
        val graph = when {
            preferences.getUser() != null -> SignupRoutes.Graph
            preferences.hasOnboarding() -> StartRoutes.Graph
            else -> StartRoutes.Graph
        }

        AppNavHost(
            navHostController = rememberNavController(), graph = graph
        )
    }
}