package br.com.fittogether.di.modules.viewmodel

import br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel.ConfirmCodeViewModel
import br.com.fittogether.presentation.feature.signup.createUser.viewmodel.CreateUserViewModel
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
            verifyEmailUseCase = get()
        )
    }

    viewModel {
        ConfirmCodeViewModel(
            email = get(),
            validateCodeUseCase = get()
        )
    }

    viewModel {
        CreateUserViewModel(
            email = get(),
            createUserUseCase = get(),
            preferences = get()
        )
    }
}