package br.com.fittogether.di.initializer

import br.com.fittogether.di.modules.core.coreModules
import br.com.fittogether.di.modules.data.dataModule
import br.com.fittogether.di.modules.domain.domainModule
import br.com.fittogether.di.modules.network.engineHttpClient
import br.com.fittogether.di.modules.network.networkModule
import br.com.fittogether.di.modules.viewmodel.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(networkModule, engineHttpClient, coreModules, viewModelModule, dataModule, domainModule)
    }
}