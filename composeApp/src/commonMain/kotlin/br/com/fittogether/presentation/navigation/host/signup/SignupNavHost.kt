package br.com.fittogether.presentation.navigation.host.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.fittogether.presentation.feature.registration.goal.screen.GoalScreen

import br.com.fittogether.presentation.feature.signup.confirmCode.screen.ConfirmCodeScreen
import br.com.fittogether.presentation.feature.signup.createUser.screen.CreateUserScreen
import br.com.fittogether.presentation.feature.registration.gender.screen.GenderScreen
import br.com.fittogether.presentation.feature.signup.verifyEmail.screen.VerifyEmailScreen
import br.com.fittogether.presentation.navigation.route.registration.RegistrationRoutes
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

fun NavGraphBuilder.signupNavHost(navController: NavHostController, startDestination: SignupRoutes) {
    navigation<SignupRoutes.Graph>(
        startDestination = startDestination
    ) {
        composable<SignupRoutes.VerifyEmail> {
            VerifyEmailScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToConfirmCode = {
                    navController.navigate(SignupRoutes.ConfirmCode)
                },
                navigateToRegistration = {
                    navController.navigate(SignupRoutes.CreateUser)
                },
                navigateToLogin = {
                    navController.navigate(StartRoutes.Login)
                }
            )
        }

        composable<SignupRoutes.ConfirmCode> {
            ConfirmCodeScreen(
                navigateBack = {
                    navController.popBackStack()

                },
                navigateToCreateUser = {
                    navController.navigate(SignupRoutes.CreateUser) {
                        popUpTo(SignupRoutes.ConfirmCode){ inclusive = true }
                    }
                }
            )
        }

        composable<SignupRoutes.CreateUser> {
            CreateUserScreen(
                navigateToGender = {
                    navController.navigate(RegistrationRoutes.Gender) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                 navigateBack = {
                     navController.popBackStack()
                 }
            )
        }
    }
}