package br.com.fittogether.di.modules.data

import br.com.fittogether.data.repository.authentication.AuthenticationRepositoryImpl
import br.com.fittogether.data.repository.signup.SignupRepositoryImpl
import br.com.fittogether.domain.repository.authentication.AuthenticationRepository
import br.com.fittogether.domain.repository.signup.SignupRepository
import org.koin.dsl.module

val dataModule = module {
    single<SignupRepository> {
        SignupRepositoryImpl(
            signupAPI = get(),
            wrapper = get()
        )
    }

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            authenticationAPI = get(),
            wrapper = get()
        )
    }
}