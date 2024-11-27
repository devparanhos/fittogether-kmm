package br.com.fittogether.presentation.feature.login.state

import br.com.fittogether.domain.model.error.ApiError
import br.com.fittogether.presentation.common.dialogs.DialogType

data class LoginState(
    val email: String = "",
    val password: String = "",
    val navigateToGender: Boolean = false,
    val navigateToGoals: Boolean = false,
    val isRequesting: Boolean = false,
    val error: ApiError? = null,
    val openDialog: Boolean = false,
    val dialogType: DialogType? = null,
    val fieldErrors: Map<String?, String?>? = null
)