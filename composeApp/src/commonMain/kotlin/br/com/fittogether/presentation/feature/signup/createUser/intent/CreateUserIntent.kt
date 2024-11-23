package br.com.fittogether.presentation.feature.signup.createUser.intent

sealed class CreateUserIntent {
    data class UpdateName(val name: String) : CreateUserIntent()
    data class UpdateCellphone(val cellphone: String) : CreateUserIntent()
    data class UpdateBirthdate(val birthdate: String) : CreateUserIntent()
    data class UpdatePassword(val password: String) : CreateUserIntent()
    data class UpdateConfirmPassword(val confirmPassword: String) : CreateUserIntent()
    data class UpdateUsername(val username: String) : CreateUserIntent()
    data object SaveUser : CreateUserIntent()
    data object OpenCloseDialog : CreateUserIntent()
}