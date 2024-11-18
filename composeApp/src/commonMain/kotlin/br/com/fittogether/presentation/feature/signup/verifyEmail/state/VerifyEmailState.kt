package br.com.fittogether.presentation.feature.signup.verifyEmail.state

import br.com.fittogether.data.remote.wrapper.ApiError

data class VerifyEmailState(
    val email: String = "",
    val isRequesting: Boolean = false,
    val navigateToConfirmCode: Boolean = false,
    val error: ApiError? = null
)