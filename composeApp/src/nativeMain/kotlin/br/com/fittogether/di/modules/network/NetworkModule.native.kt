package br.com.fittogether.di.modules.network

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val engineHttpClient: Module = module {
    single {
        Darwin.create()
    }
}
