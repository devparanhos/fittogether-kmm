package br.com.fittogether.presentation.navigation.host.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.core.enums.RegistrationStep
import br.com.fittogether.presentation.navigation.host.signup.signupNavHost
import br.com.fittogether.presentation.navigation.host.start.startNavHost
import br.com.fittogether.presentation.navigation.route.signup.SignupRoutes
import br.com.fittogether.presentation.navigation.route.start.StartRoutes

import org.koin.compose.koinInject

@Composable
fun AppNavHost(navHostController: NavHostController, graph: Any) {
    val preferences = koinInject<PreferenceController>()

    NavHost(
        navController = navHostController,
        startDestination = graph
    ) {
        startNavHost(
            navController = navHostController,
            startDestination = if (preferences.hasOnboarding()){
                StartRoutes.Start
            } else {
                StartRoutes.Welcome
            }
        )

        signupNavHost(
            navController = navHostController,
            startDestination = preferences.getUser()?.let { user ->
                when(user.registrationStep) {
                    RegistrationStep.GENDER -> {
                        SignupRoutes.Gender
                    }

                    RegistrationStep.GOALS -> {
                        SignupRoutes.Goal
                    }

                    else -> {
                        SignupRoutes.VerifyEmail
                    }
                }
            } ?: run {
                SignupRoutes.VerifyEmail
            }

        )
    }
}