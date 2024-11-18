package br.com.fittogether.presentation.feature.signup.verifyEmail.state

data class VerifyEmailState(
    val email: String = "",
    val isRequesting: Boolean = false,
    val navigateToConfirmCode: Boolean = false
)