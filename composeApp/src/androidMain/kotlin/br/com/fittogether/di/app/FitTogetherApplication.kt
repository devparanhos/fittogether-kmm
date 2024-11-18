package br.com.fittogether.di.app

import android.app.Application
import br.com.fittogether.di.initializer.initKoin
import org.koin.android.ext.koin.androidContext

class FitTogetherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FitTogetherApplication)
        }
    }

}