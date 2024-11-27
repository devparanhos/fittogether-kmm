package br.com.fittogether.presentation.navigation.host.start

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.fittogether.presentation.feature.login.screen.LoginScreen

import br.com.fittogether.presentation.feature.onboarding.screen.OnboardingScreen
import br.com.fittogether.presentation.feature.start.screen.StartScreen
import br.com.fittogether.presentation.feature.welcome.screen.WelcomeScreen
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

fun NavGraphBuilder.startNavHost(navController: NavHostController, startDestination: StartRoutes) {
    navigation<StartRoutes.Graph>(startDestination = startDestination) {
        composable<StartRoutes.Welcome> {
            WelcomeScreen(
                navigateOnboarding = {
                    navController.navigate(
                        StartRoutes.Onboarding
                    ) {
                        popUpTo(StartRoutes.Welcome){ inclusive = true }
                    }
                },
                navigateToLogin = {
                    navController.navigate(
                        StartRoutes.Start
                    ) {
                        popUpTo(StartRoutes.Welcome){ inclusive = true }
                    }
                }
            )
        }

        composable<StartRoutes.Onboarding> {
            OnboardingScreen {
                navController.navigate(StartRoutes.Start) {
                    popUpTo(StartRoutes.Onboarding) { inclusive = true }
                }
            }
        }

        composable<StartRoutes.Start> {
            StartScreen(
                navigateToLogin = {
                    navController.navigate(StartRoutes.Login)
                },
                navigateToSignup = {
                    navController.navigate(SignupRoutes.VerifyEmail)
                }
            )
        }

        composable<StartRoutes.Login> {
            LoginScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToGender = {
                    navController.navigate(SignupRoutes.Gender) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                navigateToGoals = {
                    navController.navigate(SignupRoutes.Goal) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}