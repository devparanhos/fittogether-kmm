package br.com.fittogether.di.modules.network

import br.com.fittogether.data.remote.api.authentication.AuthenticationAPI
import br.com.fittogether.data.remote.api.signup.SignupAPI
import br.com.fittogether.data.remote.client.HttpClientManager

import org.koin.core.module.Module
import org.koin.dsl.module

expect val engineHttpClient : Module

val networkModule = module {
    single {
        HttpClientManager(
            engine = get(),
            preferenceController = get()
        )
    }

    single {
        SignupAPI(
            httpClientManager = get()
        )
    }

    single {
        AuthenticationAPI(
            httpClientManager = get()
        )
    }

    factory {
        get<HttpClientManager>()::refreshHttpClient
    }
}