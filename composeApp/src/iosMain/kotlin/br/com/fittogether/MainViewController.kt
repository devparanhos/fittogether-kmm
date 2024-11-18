package br.com.fittogether

import androidx.compose.ui.window.ComposeUIViewController
import br.com.fittogether.di.initializer.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
