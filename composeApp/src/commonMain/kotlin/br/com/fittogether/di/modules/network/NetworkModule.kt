package br.com.fittogether.di.modules.network

import br.com.fittogether.data.remote.api.signup.SignupAPI
import br.com.fittogether.data.remote.client.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

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
}