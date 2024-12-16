package br.com.fittogether.presentation.navigation.host.registration

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.fittogether.presentation.feature.registration.gender.screen.GenderScreen
import br.com.fittogether.presentation.feature.registration.goal.screen.GoalScreen
import br.com.fittogether.presentation.navigation.route.registration.RegistrationRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

fun NavGraphBuilder.registrationNavHost(
    navHostController: NavHostController,
    startDestination: RegistrationRoutes = RegistrationRoutes.Gender
) {
    navigation<RegistrationRoutes.Graph>(
        startDestination = startDestination
    ) {
        composable<RegistrationRoutes.Gender> {
            GenderScreen(
                navigateToStart = {
                    navHostController.navigate(StartRoutes.Start)
                },
                navigateToGoals = {
                    navHostController.navigate(RegistrationRoutes.Goal)
                }
            )
        }

        composable<RegistrationRoutes.Goal> {
            GoalScreen(
                navigateToStart = {
                    navHostController.navigate(StartRoutes.Start)
                }
            )
        }
    }
}