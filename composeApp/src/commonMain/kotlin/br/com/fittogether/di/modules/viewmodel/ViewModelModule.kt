package br.com.fittogether.di.modules.viewmodel

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
}