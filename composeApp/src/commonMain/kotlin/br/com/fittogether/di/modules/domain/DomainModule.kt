package br.com.fittogether.di.modules.domain

import br.com.fittogether.domain.usecase.login.LoginUseCase
import br.com.fittogether.domain.usecase.signup.CreateUserUseCase
import br.com.fittogether.domain.usecase.signup.GetGenderUseCase
import br.com.fittogether.domain.usecase.signup.GetGoalUseCase
import br.com.fittogether.domain.usecase.signup.SetGenderUseCase
import br.com.fittogether.domain.usecase.signup.SetGoalsUseCase
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

    factory {
        LoginUseCase(
            repository = get()
        )
    }

    factory {
        GetGenderUseCase(repository = get())
    }

    factory {
        SetGenderUseCase(repository = get())
    }

    factory {
        GetGoalUseCase(repository = get())
    }

    factory {
        SetGoalsUseCase(repository = get())
    }
}