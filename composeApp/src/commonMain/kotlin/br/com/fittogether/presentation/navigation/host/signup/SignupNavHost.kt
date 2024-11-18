package br.com.fittogether.presentation.navigation.host.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute

import br.com.fittogether.presentation.feature.signup.confirmCode.screen.ConfirmCodeScreen
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
                navigateToConfirmCode = {
                    navController.navigate(SignupRoutes.ConfirmCode())
                }
            )
        }

        composable<SignupRoutes.ConfirmCode> {
            ConfirmCodeScreen(
                email = it.toRoute<SignupRoutes.ConfirmCode>().email
            )
        }
    }
}