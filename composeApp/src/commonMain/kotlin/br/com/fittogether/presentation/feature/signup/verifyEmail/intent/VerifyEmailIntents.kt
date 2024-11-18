package br.com.fittogether.presentation.feature.signup.verifyEmail.intent

sealed class VerifyEmailIntents {
    data class UpdateEmail(val email: String) : VerifyEmailIntents()
    data object SendEmail : VerifyEmailIntents()
}