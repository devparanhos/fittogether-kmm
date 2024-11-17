package br.com.fittogether.presentation.navigation.host.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.fittogether.presentation.navigation.host.signup.signupNavHost
import br.com.fittogether.presentation.navigation.host.start.startNavHost
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = StartRoutes.Graph
    ) {
        startNavHost(navController = navHostController)

        signupNavHost(navController = navHostController)
    }
}