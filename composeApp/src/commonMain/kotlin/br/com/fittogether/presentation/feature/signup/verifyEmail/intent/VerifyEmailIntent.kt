package br.com.fittogether.presentation.feature.signup.verifyEmail.intent

sealed class VerifyEmailIntent {
    data class UpdateEmail(val email: String) : VerifyEmailIntent()
    data object SendEmail : VerifyEmailIntent()
    data object Clear : VerifyEmailIntent()
}