package br.com.fittogether.di.modules.network

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val engineHttpClient = module {
    single {
       OkHttp.create()
    }
}
