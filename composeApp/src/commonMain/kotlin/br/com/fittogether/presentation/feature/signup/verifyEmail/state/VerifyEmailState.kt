package br.com.fittogether.presentation.feature.signup.verifyEmail.state

import br.com.fittogether.domain.model.error.ApiError
import br.com.fittogether.presentation.common.dialogs.DialogType

data class VerifyEmailState(
    val email: String = "",
    val isRequesting: Boolean = false,
    val navigateToConfirmCode: Boolean = false,
    val navigateToRegistration: Boolean = false,
    val error: ApiError? = null,
    val openDialog: Boolean = false,
    val dialogType: DialogType? = null
)