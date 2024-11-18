package br.com.fittogether.di.modules.core

import br.com.fittogether.core.controller.PreferenceController
import br.com.fittogether.data.remote.wrapper.ApiWrapper
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val coreModules = module {
    single {
        Settings()
    }

    single {
        PreferenceController(
            settings = get()
        )
    }

    single {
        ApiWrapper()
    }
}