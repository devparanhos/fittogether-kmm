package br.com.fittogether.presentation.feature.login.intent

sealed class LoginIntent {
    data class UpdateEmail(val email: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    data object OpenCloseDialog : LoginIntent()
    data object Login : LoginIntent()
}