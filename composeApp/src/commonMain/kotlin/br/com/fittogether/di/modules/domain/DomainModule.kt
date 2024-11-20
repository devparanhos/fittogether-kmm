package br.com.fittogether.di.modules.domain

import br.com.fittogether.domain.usecase.signup.CreateUserUseCase
import br.com.fittogether.domain.usecase.signup.ValidateCodeUseCase
import br.com.fittogether.domain.usecase.signup.VerifyEmailUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        VerifyEmailUseCase(
            repository = get()
        )
    }

    factory {
        ValidateCodeUseCase(
            repository = get()
        )
    }

    factory {
        CreateUserUseCase(
            repository = get()
        )
    }
}