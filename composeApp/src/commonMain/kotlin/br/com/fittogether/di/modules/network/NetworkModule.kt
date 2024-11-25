package br.com.fittogether.di.modules.network

import br.com.fittogether.data.remote.api.authentication.AuthenticationAPI
import br.com.fittogether.data.remote.api.signup.SignupAPI
import br.com.fittogether.data.remote.client.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.math.sin

expect val engineHttpClient : Module

val networkModule = module {
    single {
        createHttpClient(engine = get())
    }

    single {
        SignupAPI(
            httpClient = get()
        )
    }

    single {
        AuthenticationAPI(
            httpClient = get()
        )
    }
}