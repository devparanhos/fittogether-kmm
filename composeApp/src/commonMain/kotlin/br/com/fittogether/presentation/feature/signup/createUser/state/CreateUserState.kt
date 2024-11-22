package br.com.fittogether.presentation.feature.signup.createUser.state

import br.com.fittogether.domain.model.error.ApiError
import br.com.fittogether.presentation.common.dialogs.DialogType

data class CreateUserState(
    val email: String = "",
    val cellphone: String = "",
    val name: String = "",
    val birthdate: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val error: ApiError? = null,
    val openDialog: Boolean = false,
    val dialogType: DialogType? = null,
    val fieldErrors: Map<String?, String?>? = null,
    val isRequesting: Boolean = false
)