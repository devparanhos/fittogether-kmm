package br.com.fittogether.presentation.feature.signup.confirmCode.state

import br.com.fittogether.domain.model.error.ApiError
import br.com.fittogether.presentation.common.dialogs.DialogType

data class ConfirmCodeState(
    val email: String = "",
    val listCodes : MutableList<String> = mutableListOf("", "", "", "", "", ""),
    val secondsLeft: Int = 100,
    val canResendCode: Boolean = false,
    val navigateToCreateUser: Boolean = false,
    val isVerifyingCode: Boolean = false,
    val error: ApiError? = null,
    val openDialog: Boolean = false,
    val dialogType: DialogType? = null,
    val fieldErrors: Map<String?, String?>? = null,
    val isResendingCode: Boolean = false
)