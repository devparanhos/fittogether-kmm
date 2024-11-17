package br.com.fittogether

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import br.com.fittogether.presentation.navigation.host.app.AppNavHost
import br.com.fittogether.presentation.ui.theme.FitTogetherTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    FitTogetherTheme {
        AppNavHost(navHostController = rememberNavController())
    }
}