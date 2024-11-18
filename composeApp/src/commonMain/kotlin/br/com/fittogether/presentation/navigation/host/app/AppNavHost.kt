package br.com.fittogether.presentation.navigation.host.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.presentation.navigation.host.signup.signupNavHost
import br.com.fittogether.presentation.navigation.host.start.startNavHost
import br.com.fittogether.presentation.navigation.route.start.StartRoutes
import org.koin.compose.koinInject

@Composable
fun AppNavHost(navHostController: NavHostController) {
    val preferences = koinInject<PreferenceController>()
    val startDestination = when {
        preferences.hasOnboarding() -> {
            StartRoutes.Start
        }
        else -> {
            StartRoutes.Welcome
        }
    }

    NavHost(
        navController = navHostController,
        startDestination = StartRoutes.Graph
    ) {
        startNavHost(navController = navHostController, startDestination = startDestination)

        signupNavHost(navController = navHostController)
    }
}