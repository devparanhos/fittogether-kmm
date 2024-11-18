package br.com.fittogether

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import br.com.fittogether.presentation.navigation.host.app.AppNavHost
import br.com.fittogether.presentation.ui.theme.FitTogetherTheme

@Composable
fun App() {
    FitTogetherTheme {
        AppNavHost(navHostController = rememberNavController())
    }
}