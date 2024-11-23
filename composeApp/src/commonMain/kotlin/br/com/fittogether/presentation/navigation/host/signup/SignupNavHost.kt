package br.com.fittogether.presentation.navigation.host.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute

import br.com.fittogether.presentation.feature.signup.confirmCode.screen.ConfirmCodeScreen
import br.com.fittogether.presentation.feature.signup.createUser.screen.CreateUserScreen
import br.com.fittogether.presentation.feature.signup.gender.screen.GenderScreen
import br.com.fittogether.presentation.feature.signup.verifyEmail.screen.VerifyEmailScreen
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

fun NavGraphBuilder.signupNavHost(navController: NavHostController) {
    navigation<SignupRoutes.Graph>(
        startDestination = SignupRoutes.VerifyEmail
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
                    navController.navigate(SignupRoutes.CreateUser)
                }
            )
        }

        composable<SignupRoutes.CreateUser> {
            CreateUserScreen(
                navigateToGender = {
                    navController.navigate(SignupRoutes.Gender)
                }
            )
        }

        composable<SignupRoutes.Gender> {
            GenderScreen()
        }
    }
}