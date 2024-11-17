package br.com.fittogether.presentation.navigation.host.start

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

import br.com.fittogether.presentation.feature.onboarding.screen.OnboardingScreen
import br.com.fittogether.presentation.feature.start.screen.StartScreen
import br.com.fittogether.presentation.feature.welcome.screen.WelcomeScreen
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

fun NavGraphBuilder.startNavHost(navController: NavHostController) {
    navigation<StartRoutes.Graph>(startDestination = StartRoutes.Welcome) {
        composable<StartRoutes.Welcome> {
            WelcomeScreen(
                navigateOnboarding = {
                    navController.navigate(
                        StartRoutes.Onboarding
                    )
                }
            )
        }

        composable<StartRoutes.Onboarding> {
            OnboardingScreen {
                navController.navigate(StartRoutes.Start)
            }
        }

        composable<StartRoutes.Start> {
            StartScreen {
                navController.navigate(SignupRoutes.Graph)
            }
        }
    }
}