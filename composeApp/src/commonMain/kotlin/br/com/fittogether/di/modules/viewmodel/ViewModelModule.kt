package br.com.fittogether.di.modules.viewmodel

import br.com.fittogether.presentation.feature.login.viewmodel.LoginViewModel
import br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel.ConfirmCodeViewModel
import br.com.fittogether.presentation.feature.signup.createUser.viewmodel.CreateUserViewModel
import br.com.fittogether.presentation.feature.signup.gender.viewmodel.GenderViewModel
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
            preferences = get()
        )
    }

    viewModel {
        LoginViewModel(
            preferenceController = get(),
            loginUseCase = get()
        )
    }

    viewModel {
        GenderViewModel(
            preferenceController = get()
        )
    }
}