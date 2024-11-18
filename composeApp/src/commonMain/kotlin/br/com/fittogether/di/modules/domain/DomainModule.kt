package br.com.fittogether.di.modules.domain

import br.com.fittogether.domain.usecase.signup.VerifyEmailUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        VerifyEmailUseCase(
            repository = get()
        )
    }
}