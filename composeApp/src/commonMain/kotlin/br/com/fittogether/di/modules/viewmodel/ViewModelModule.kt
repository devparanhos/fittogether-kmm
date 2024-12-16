package br.com.fittogether.di.modules.viewmodel

import br.com.fittogether.data.remote.client.HttpClientManager
import br.com.fittogether.presentation.feature.login.viewmodel.LoginViewModel
import br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel.ConfirmCodeViewModel
import br.com.fittogether.presentation.feature.signup.createUser.viewmodel.CreateUserViewModel
import br.com.fittogether.presentation.feature.registration.gender.viewmodel.GenderViewModel
import br.com.fittogether.presentation.feature.registration.goal.viewmodel.GoalViewModel
import br.com.fittogether.presentation.feature.signup.verifyEmail.viewmodel.VerifyEmailViewModel
import br.com.fittogether.presentation.feature.start.viewmodel.StartViewModel

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StartViewModel(
            preferences = get()
        )
    }

    viewModel {
        VerifyEmailViewModel(
            preferences = get(),
            verifyEmailUseCase = get()
        )
    }

    viewModel {
        ConfirmCodeViewModel(
            preferences = get(),
            validateCodeUseCase = get(),
            verifyEmailUseCase = get()
        )
    }

    viewModel {
        CreateUserViewModel(
            createUserUseCase = get(),
            preferences = get(),
            refreshHttpClient = get<HttpClientManager>()::refreshHttpClient
        )
    }

    viewModel {
        LoginViewModel(
            preferenceController = get(),
            loginUseCase = get(),
            refreshHttpClient = get<HttpClientManager>()::refreshHttpClient
        )
    }

    viewModel {
        GenderViewModel(
            preferenceController = get(),
            getGenderUseCase = get(),
            setGenderUseCase = get()
        )
    }

    viewModel {
        GoalViewModel(
            preferences = get(),
            getGoalsUseCase = get()
        )
    }
}