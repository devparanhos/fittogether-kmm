package br.com.fittogether.presentation.navigation.host.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute

import br.com.fittogether.presentation.feature.signup.confirmCode.screen.ConfirmCodeScreen
import br.com.fittogether.presentation.feature.signup.createUser.screen.CreateUserScreen
import br.com.fittogether.presentation.feature.signup.verifyEmail.screen.VerifyEmailScreen
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes

fun NavGraphBuilder.signupNavHost(navController: NavHostController) {
    navigation<SignupRoutes.Graph>(
        startDestination = SignupRoutes.VerifyEmail
    ) {
        composable<SignupRoutes.VerifyEmail> {
            VerifyEmailScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToConfirmCode = { email ->
                    navController.navigate(SignupRoutes.ConfirmCode(email = email))
                }
            )
        }

        composable<SignupRoutes.ConfirmCode> {
            ConfirmCodeScreen(
                email = it.toRoute<SignupRoutes.ConfirmCode>().email,
                navigateBack = {
                    navController.popBackStack()

                },
                navigateToCreateUser = {
                    navController.navigate(SignupRoutes.CreateUser(email = it))
                }
            )
        }

        composable<SignupRoutes.CreateUser> {
            CreateUserScreen(
                email = it.toRoute<SignupRoutes.ConfirmCode>().email
            )
        }
    }
}